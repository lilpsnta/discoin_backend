<template>
  <div>
    <a-row :gutter="16">
      <a-col :span="8">
        <a-card  title="项目信息" style="height:650px;" size="small" >
          <a-spin :spinning="confirmLoading">
            <j-form-container :disabled="formDisabled">
              <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
                <a-row>
                  <a-col :span="24">
                    <a-form-model-item label="项目名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="projectName">
                      <a-input v-model="model.projectName" placeholder="请输入项目名称"  ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :span="24">
                    <a-form-model-item label="项目位置" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="projectLocation">
                      <a-input v-model="model.projectLocation" placeholder="请输入项目位置"  ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :span="24">
                    <a-form-model-item label="预计服务期限" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="serviceTerm">
                      <a-input v-model="model.serviceTerm" placeholder="请输入预计服务期限"  ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :span="24">
                    <a-form-model-item label="参建单位名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="partnerCompanyName">
                      <a-input v-model="model.partnerCompanyName" placeholder="请输入参建单位名称"  ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :span="24">
                    <a-form-model-item label="路面结构类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="roadStructureType">
                      <j-dict-select-tag v-model="model.roadStructureType" placeholder="请选择路面结构类型" dictCode="road_structure_type"/>
                    </a-form-model-item>
                  </a-col>
                  <a-col :span="24">
                    <a-form-model-item label="成果产值比例" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="resultRatio">
                      <a-input v-model="model.resultRatio" placeholder="请输入成果产值比例"  ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :span="24">
                    <a-form-model-item label="项目经理" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="projectManagerId">
                      <j-select-multi-user placeholder="请选择用户" :multiple="false" v-model="model.projectManagerId"/>
                    </a-form-model-item>
                  </a-col>
                  <a-col :span="24">
                    <a-form-model-item label="项目状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="projectStatus">
                      <j-dict-select-tag v-model="model.projectStatus" placeholder="请选择项目状态" dictCode="project_status"/>
                    </a-form-model-item>
                  </a-col>
                  <a-col :span="24">
                    <a-form-model-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="intro">
                      <a-input v-model="model.intro" placeholder="请输入备注"  ></a-input>
                    </a-form-model-item>
                  </a-col>
                </a-row>
              </a-form-model>
            </j-form-container>
          </a-spin>
        </a-card>
      </a-col>
      <a-col :span="16">
          <a-card  title="项目标段" style="height:300px;"  size="small">
            <a-button type="primary" @click="addSection" >添加</a-button>
            <a-table :columns="sectionColumns" :data-source="sectionDatasource" bordered size="small" style="margin-top:3px;" :scroll="{ x: 700, y: 170 }" :pagination="false">
              <a slot="name" slot-scope="text">{{ text }}</a>
              <span slot="customTitle"><a-icon type="smile-o" /> Name</span>
              <span slot="tags" slot-scope="tags">
                <a-tag
                  v-for="tag in tags"
                  :key="tag"
                  :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"
                >
                  {{ tag.toUpperCase() }}
                </a-tag>
              </span>
              <template slot="action" slot-scope="text, record">
                <a href="javascript:;" @click="editSection(record)">修改</a>
                <a href="javascript:;" @click="deleteSection(record.id)" style="margin-left:10px;">删除</a>
              </template>
            </a-table>
          </a-card>


          <!-- 项目成员 -->
          <a-card  title="项目成员" style="height:345px;margin-top:5px;"  size="small">
            <a-button type="primary" @click="addMember" >添加</a-button>
            <a-table :columns="memberColumns" :data-source="memberDatasource" bordered size="small"  style="margin-top:3px;height:300px;" :scroll="{ x: 700, y: 170 }" :pagination="false">
              
              <template slot="action" slot-scope="text, record">
                <a href="javascript:;" @click="editMember(record)">修改</a>
                <a href="javascript:;" @click="deleteMember(record.id)" style="margin-left:10px;">删除</a>
              </template>
            </a-table>
          </a-card>
      </a-col>
    </a-row>

    <section-model ref="sectionModalForm" @ok="selectModalFormOk"></section-model>
    <member-model ref="memberModalForm" @ok="memberModalFormOk"></member-model>
  </div>

  

</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'
  import JEditableTable from '../../../components/jeecg/JEditableTable.vue'
  import JSelectMultiUser from '../../../components/jeecgbiz/JSelectMultiUser.vue'
  import SectionModel from './SectionModel.vue'
  import MemberModel from './MemberModel.vue'
  import {queryProjectSection,queryProjectMember,deleteProjectSection, deleteProjectMember} from '@/api/api'
  

  export default {
    name: 'ProjectForm',
    components: {
        JEditableTable,
        JSelectMultiUser,
        SectionModel,
        MemberModel
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
        },

        memberColumns: [
          {
            title : '姓名',
            dataIndex: 'realname',
            key: 'realname'
          },
          {
            title : '职称',
            dataIndex: 'post',
            key: 'post'
          },
          {
            title : '年龄',
            dataIndex: 'age',
            key: 'age'
          },
          {
            title : '学历',
            dataIndex: 'edu',
            key: 'edu'
          },
          {
            title : '证书',
            dataIndex: 'cert',
            key: 'cert'
          },
          {
            title : '工作年限',
            dataIndex: 'workAge',
            key: 'workAge'
          },
          {
            title : '工作简历',
            dataIndex: 'resume',
            key: 'resume'
          },
          {
            title : '操作',
            dataIndex: 'action',
            scopedSlots: { customRender: 'action' }
          }
        ],
        memberDatasource : [],
        sectionColumns: [
          {
            title : '标段名称',
            dataIndex : 'sectionName',
            key : 'sectionName'
          },
          {
            title : '机械设备数量',
            dataIndex : 'deviceNum',
            key : 'deviceNum'
          },
          {
            title : '机械设备型号',
            dataIndex : 'deviceModel',
            key : 'deviceModel'
          },
          {
            title : '拌合楼型号',
            dataIndex : 'bhlModel',
            key : 'bhlModel'
          },
          {
            title : '操作',
            dataIndex: 'action',
            scopedSlots: { customRender: 'action' }
          }
        ],
        sectionDatasource:[],
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
          add: "/project/add",
          edit: "/project/edit",
          queryById: "/project/queryById"
        },
        projectId: ''
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
      // 标段操作返回成功
      selectModalFormOk(){
        this.loadSectionData();
      },
      // 成员操作返回成功
      memberModalFormOk(){
        this.loadMemberData();
      },
      // 查询路段信息
      loadSectionData() {
        let that = this;
        var params = {projectId : this.projectId};
        this.loading = true;
        queryProjectSection(params).then(res=>{
          that.loading = false;
          if(res.success){
            let sectionList = res.result;
            that.sectionDatasource = sectionList;
          }else{
            that.$message.error(res.message)
          }
        })
      },

      // 查询成员信息
      loadMemberData() {
        let that = this;
        var params = {projectId : this.projectId};
        this.loading = true;
        queryProjectMember(params).then(res=>{
          that.loading = false;
          if(res.success){
            let memberList = res.result;
            that.memberDatasource = memberList;
          }else{
            that.$message.error(res.message)
          }
        })
      },

      add () {
        this.edit(this.modelDefault);
      },
      // 编辑
      edit (record) {
        this.projectId = record.id
        this.model = Object.assign({}, record);
        //this.sectionDatasource = record.sectionList;
        //this.memberDatasource = record.memberList;
        
        this.loadSectionData();
        this.loadMemberData();
        
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
      editSection(record){
        // 编辑标段
        this.$refs.sectionModalForm.edit(record);
        this.$refs.sectionModalForm.title = "编辑标段";
        //this.$refs.sectionModalForm.disableSubmit = false;
      },
      deleteSection(id){


        // 删除标段
        var that = this;
        let params = {id : id};
        
        this.$confirm({
          title: "确认删除",
          content: "确定要该项目标段?",
          onOk: function () {
            that.loading = true;
            deleteProjectSection(params).then(res => {
              if(res.success){
                that.$message.success(res.message)
                that.loadSectionData();
              }else{
                that.$message.warning(res.message)
              }
            }).finally(() => {
              that.loading = false;
            });
          }
        });
      },
      editMember(record){
        // 编辑成员
        this.$refs.memberModalForm.edit(record);
        this.$refs.memberModalForm.title = "编辑标段";
      },
      deleteMember(id){
        // 删除成员
        var that = this;
        let params = {id : id};
        this.$confirm({
          title: "确认删除",
          content: "确定要该项目成员?",
          onOk: function () {
            that.loading = true;
            deleteProjectMember(params).then(res => {
              if(res.success){
                that.$message.success(res.message)
                that.loadMemberData();
              }else{
                that.$message.warning(res.message)
              }
            }).finally(() => {
              that.loading = false;
            });
          }
        });
      },
      addSection(){
        this.$refs.sectionModalForm.add(this.projectId);
        this.$refs.sectionModalForm.title = "新增标段";
      },
      addMember(){
        this.$refs.memberModalForm.add(this.projectId);
        this.$refs.memberModalForm.title = "新增项目成员";
      }
    }
  }
</script>