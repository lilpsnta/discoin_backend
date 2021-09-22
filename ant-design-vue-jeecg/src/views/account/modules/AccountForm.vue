<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="账户地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="token">
              <a-input v-model="model.token" placeholder="请输入账户地址"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="账户名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="userId">
              <a-input v-model="model.userId" placeholder="请输入账户名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="账户余额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="balance">
              <a-input-number v-model="model.balance" placeholder="请输入账户余额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="冻结金额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="lockedAmount">
              <a-input-number v-model="model.lockedAmount" placeholder="请输入冻结金额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="累计金额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="totalAmount">
              <a-input-number v-model="model.totalAmount" placeholder="请输入累计金额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="今日收益" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="todayProfit">
              <a-input-number v-model="model.todayProfit" placeholder="请输入今日收益" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="预计收益" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="predictProfit">
              <a-input-number v-model="model.predictProfit" placeholder="请输入预计收益" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="收益周期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="profitTerm">
              <a-input-number v-model="model.profitTerm" placeholder="请输入收益周期" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="收益类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="profitType">
              <j-dict-select-tag type="list" v-model="model.profitType" dictCode="" placeholder="请选择收益类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="收益占比" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="profitRadio">
              <a-input-number v-model="model.profitRadio" placeholder="请输入收益占比" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="固定收益" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="fixedProfit">
              <a-input-number v-model="model.fixedProfit" placeholder="请输入固定收益" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="每日提币上限" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="dayLimitAmount">
              <a-input-number v-model="model.dayLimitAmount" placeholder="请输入每日提币上限" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="管理费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="managementFee">
              <a-input-number v-model="model.managementFee" placeholder="请输入管理费" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="手续费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tradeFee">
              <a-input-number v-model="model.tradeFee" placeholder="请输入手续费" style="width: 100%" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'AccountForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
            profitType:"1",
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/account/add",
          edit: "/account/edit",
          queryById: "/account/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>