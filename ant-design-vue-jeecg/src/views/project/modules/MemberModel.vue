<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭"
  >
    <a-spin :spinning="confirmLoading">
      <a-form-model  ref="form" :model="model" :rules="validatorRules">

        <a-form-model-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          prop="memberId"
          label="项目成员">
          <j-select-multi-user placeholder="请选择用户" :multiple="false" v-model="model.memberId"/>
        </a-form-model-item>

        <a-form-model-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          prop="memberType"
          label="成员类型">
          <j-dict-select-tag v-model="model.memberType" placeholder="请选成员类型" dictCode="project_member_type"/>
        </a-form-model-item>
        
      </a-form-model>
    </a-spin>
  </a-modal>
</template>

<script>
  import pick from 'lodash.pick'
  import {addProjectMember,editProjectMember} from '@/api/api'
  import { getAction } from '@api/manage'

  export default {
    name: "MemberModal",
    data() {
      return {
        title: "操作",
        visible: false,
        visibleCheck: true,
        model: {},
        projectId: "",
        status: 1,
        labelCol: {
          xs: {span: 24},
          sm: {span: 5},
        },
        wrapperCol: {
          xs: {span: 24},
          sm: {span: 16},
        },
        confirmLoading: false,
        validatorRules: {
          itemText:  [{required: true, message: '请输入名称!'}]
        },
      }
    },
    created() {
    },
    methods: {
      add(projectId) {
        this.projectId = projectId;
        this.edit({});
      },
      edit(record) {
        if (record.id) {
          this.projectId = record.projectId;
        }
        this.model = Object.assign({}, record);
        this.model.projectId = this.projectId;
        this.visible = true;
      },
      // 确定
      handleOk() {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            this.model.memberId = (this.model.memberId || '').trim()
            this.model.memberType = (this.model.memberType || '').trim()
            let obj;
            if (!this.model.id) {
              obj = addProjectMember(this.model);
            } else {
              obj = editProjectMember(this.model);
            }
            obj.then((res) => {
              if (res.success) {
                that.$message.success(res.message);
                that.$emit('ok');
              } else {
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }else{
            return false;
          }
        })
      },
      // 关闭
      handleCancel() {
        this.close();
      },
      close() {
        this.$emit('close');
        this.visible = false;
        this.$refs.form.resetFields();
      }
    }
  }
</script>
