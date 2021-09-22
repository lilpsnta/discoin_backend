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
          prop="sectionName"
          label="标段名称">
          <a-input placeholder="请输入标段名称" v-model="model.sectionName"/>
        </a-form-model-item>

        <a-form-model-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          prop="deviceNum"
          label="机械设备数量">
          <a-input placeholder="请输机械设备数量" v-model="model.deviceNum" />
        </a-form-model-item>

        <a-form-model-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          prop="deviceModel"
          label="机械设备型号">
          <a-input v-model="model.deviceModel" />
        </a-form-model-item>

        <a-form-model-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          prop="bhlModel"
          label="拌合楼型号">
          <a-input v-model="model.bhlModel" />
        </a-form-model-item>
      </a-form-model>
    </a-spin>
  </a-modal>
</template>

<script>
  import pick from 'lodash.pick'
  import {addProjectSection,editProjectSection} from '@/api/api'
  import { getAction } from '@api/manage'

  export default {
    name: "SectionModal",
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
            this.model.sectionName = (this.model.sectionName || '').trim()
            this.model.deviceNum = (this.model.deviceNum || '').trim()
            this.model.deviceModel = (this.model.deviceModel || '').trim()
            this.model.bhlModel = (this.model.bhlModel || '').trim()
            let obj;
            if (!this.model.id) {
              obj = addProjectSection(this.model);
            } else {
              obj = editProjectSection(this.model);
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
