<template>
  <div class="lg_container" >
    <div class="login_container"></div>
    <div class="login_content">
      <div style="text-align:center;padding-bottom:20px;">
        <span style="font-size:20px;color:#ffffff;font-weight:bold;">用户登录</span>
      </div>
      <a-form-model ref="form" :model="model" :rules="validatorRules" class="user-layout-login">
            <a-form-model-item required prop="username">
              <a-input v-model="model.username" size="large" placeholder="请输入帐户名">
                <a-icon slot="prefix" type="user" :style="{ color: 'rgba(0,0,0,.25)' }"/>
              </a-input>
            </a-form-model-item>
            <a-form-model-item required prop="password">
              <a-input v-model="model.password" size="large" type="password" autocomplete="false" placeholder="请输入密码">
                <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
              </a-input>
            </a-form-model-item>
         

        <a-form-item style="margin-top:24px">
          <a-button size="large" type="primary" htmlType="submit" class="login-button" :loading="loginBtn" @click.stop.prevent="handleSubmit" :disabled="loginBtn">确定</a-button>
        </a-form-item>

      </a-form-model>

      <login-select-tenant ref="loginSelect" @success="loginSelectOk"></login-select-tenant>
    </div>
    
  </div>
</template>

<script>
  import { postAction, getAction } from '@/api/manage'
  import Vue from 'vue'
  import { ACCESS_TOKEN ,ENCRYPTED_STRING} from "@/store/mutation-types"
  import { mapActions } from "vuex"
  import ThirdLogin from './third/ThirdLogin'
  import LoginSelectTenant from "./LoginSelectTenant"
  import TwoStepCaptcha from '@/components/tools/TwoStepCaptcha'
  import { encryption , getEncryptedString } from '@/utils/encryption/aesEncrypt'
  import { timeFix } from "@/utils/util"

  export default {
    components: {
      LoginSelectTenant,
      TwoStepCaptcha,
      ThirdLogin
    },
    data () {
      return {
        model: {
          username:'',
          password:'',
          inputCode: ''
        },
        loginType: 0,
        validatorRules:{
          username: [
            { required: true, message: '请输入用户名!' },
            { validator: this.handleUsernameOrEmail }
          ],
          password: [{
            required: true, message: '请输入密码!', validator: 'click'
          }]
        },
        customActiveKey: 'tab1',
        requestCodeSuccess: false,
        randCodeImage: '',
        currdatetime: '',
        loginBtn: false,
        requiredTwoStepCaptcha: false,
        stepCaptchaVisible: false,
        encryptedString:{
          key:"",
          iv:"",
        },
      }
    },
    created() {
      this.currdatetime = new Date().getTime();
      this.model.rememberMe = true
      Vue.ls.remove(ACCESS_TOKEN)
      this.getRouterData();
      this.handleChangeCheckCode();
    },
    methods:{
      ...mapActions(['Login', 'Logout']),
      handleTabClick(key){
        this.customActiveKey = key
      },
      handleUsernameOrEmail (rule, value, callback) {
        const regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if (regex.test(value)) {
          this.loginType = 0
        } else {
          this.loginType = 1
        }
        callback()
      },
      /**跳转到登录页面的参数-账号获取*/
      getRouterData(){
        this.$nextTick(() => {
          let temp = this.$route.params.username || this.$route.query.username || ''
          if (temp) {
            this.model['username'] = temp
          }
        })
      },
      handleRememberMeChange(e){
        this.model.rememberMe = e.target.checked
      },
      //登录
      handleSubmit () {
        let that = this
        let loginParams = {};
        that.loginBtn = true;
        // 使用账户密码登录
        if (that.customActiveKey === 'tab1') {
          this.loginByUsername();
        } else {
          this.loginByPhone()
        }
      },
      /**
       * 验证字段
       * @param arr
       * @param callback
       */
      validateFields(arr, callback){
        let promiseArray = []
        for(let item of arr){
          let p = new Promise((resolve, reject) => {
            this.$refs['form'].validateField(item, (err)=>{
              if(!err){
                resolve();
              }else{
                reject(err);
              }
            })
          });
          promiseArray.push(p)
        }
        Promise.all(promiseArray).then(()=>{
          callback()
        }).catch(err=>{
          callback(err)
        })
      },
      //账号密码登录
      loginByUsername(){
        this.validateFields([ 'username', 'password' ], (err)=>{
          if(!err){
            let loginParams = {
              username: this.model.username,
              password: this.model.password,
              remember_me: this.model.rememberMe
            }
            console.log("登录参数", loginParams)
            this.Login(loginParams).then((res) => {
              this.$refs.loginSelect.show(res.result)
            }).catch((err) => {
              this.requestFailed(err);
            });
          }else{
            this.loginBtn = false;
          }
        })
      },
      //登录后台失败
      requestFailed (err) {
        let description = ((err.response || {}).data || {}).message || err.message || "请求出现错误，请稍后再试"
        this.$notification[ 'error' ]({
          message: '登录失败',
          description: description,
          duration: 4,
        });
        //密码错误后更新验证码
        if(description.indexOf('密码错误')>0){
          this.handleChangeCheckCode();
        }
        this.loginBtn = false;
      },
      loginSelectOk(){
        this.loginSuccess()
      },
      //登录成功
      loginSuccess () {
        this.$router.push({ path: "/dashboard/Analysis" }).catch(()=>{
          console.log('登录跳转首页出错')
        })
        this.$notification.success({
          message: '欢迎',
          description: `${timeFix()}，欢迎回来`,
        });
      },
      //获取密码加密规则
      getEncrypte(){
        var encryptedString = Vue.ls.get(ENCRYPTED_STRING);
        if(encryptedString == null){
          getEncryptedString().then((data) => {
            this.encryptedString = data
          });
        }else{
          this.encryptedString = encryptedString;
        }
      }

    }

  }
</script>
<style lang="less" scoped>
  .lg_container{
    width:501px;height:351px;margin-left:-70px;margin-top:100px;
    border: 1px solid #ffffff;
    border-radius: 10px;
  }

  .login_container{
      
      width: 100%;
      height: 100%;
      background-color: #303031;
      opacity: 0.3;
      border-radius: 10px;
  }

  .login_content{
    
    width:350px;
    margin-left:70px;
    position:relative;
    top:-300px;
    left:0px;
    z-index: 100;
  }
  
  .user-layout-login {
    label {
      font-size: 14px;
    }
  .getCaptcha {
      display: block;
      width: 100%;
      height: 40px;
    }

  .forge-password {
      font-size: 14px;
    }

    button.login-button {
      padding: 0 15px;
      font-size: 16px;
      height: 40px;
      width: 100%;
    }

  .user-login-other {
      text-align: left;
      margin-top: 24px;
      line-height: 22px;

    .item-icon {
        font-size: 24px;
        color: rgba(0,0,0,.8);
        margin-left: 16px;
        vertical-align: middle;
        cursor: pointer;
        transition: color .3s;

      &:hover {
          color: #1890ff;
        }
      }

    .register {
        float: right;
      }
    }
  }
</style>
<style>
  .valid-error .ant-select-selection__placeholder{
    color: #faf8f8;
  }
</style>