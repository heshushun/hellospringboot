webpackJsonp([15],{"9Yp4":function(t,e,a){var n=a("oHgn");"string"==typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);a("rjj0")("3f5aecbe",n,!0)},Bc7o:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("woOf"),s=a.n(n),i=a("Q3je"),l=a("0xDb"),o={name:"taskList",data:function(){return{list:null,total:0,listLoading:!0,Status:[{id:"1",name:"是"},{id:"0",name:"否"}],Online:[{id:"1",name:"是"},{id:"0",name:"否"}],taskOnlineStatus:!1,listQuery:{taskStatus:"",taskOnline:"",taskName:"",current:1,limit:10},downloadLoading:!1}},filters:{statusFilter:function(t){return{1:"success",0:"danger"}[t]}},computed:{},created:function(){this.getList()},methods:{getList:function(){var t=this;this.listLoading=!0,Object(i.e)(this.listQuery).then(function(e){t.list=e.data.data.records,t.total=e.data.data.total,t.listLoading=!1}).catch(function(){t.listLoading=!1})},handleFilter:function(){this.listQuery.current=1,this.getList()},handleSizeChange:function(t){this.listQuery.limit=t,this.getList()},handleCurrentChange:function(t){this.listQuery.current=t,this.getList()},handleCreate:function(){this.getList()},handleInit:function(){var t=this;Object(i.d)().then(function(e){e.data.success?t.$message({message:"初始化启动成功",type:"success"}):t.$message({message:"初始化启动失败",type:"error"}),t.getList()}).catch(function(t){console.log(t)})},changeTaskStatus:function(t,e){var a=this;"0"===e.taskOnline?(console.log("index:"+t,"taskId:"+e.taskId,"taskStatus:"+e.taskStatus),Object(i.h)(s()({},{taskId:e.taskId,taskStatus:e.taskStatus})).then(function(t){"1"===e.taskStatus?a.$message({message:"启用成功",type:"success"}):a.$message({message:"关闭成功",type:"success"})}).catch(function(t){console.log(t)})):(e.taskStatus="1",this.$message({message:"任务正在启动，不能直接关闭，需先下线任务",type:"error"}))},handleModifyStatus:function(t,e){var a=this;"1"===t.taskStatus?(t.taskOnline=e,Object(i.g)(s()({},{taskId:t.taskId,taskOnline:e})).then(function(t){"1"===e?a.$message({message:"上线成功",type:"success"}):a.$message({message:"下线成功",type:"success"})}).catch(function(t){console.log(t)})):this.$message({message:"非启用任务，无法 【启动】",type:"error"})},deleteById:function(t){var e=this;this.$confirm("此操作将永久删除任务 "+t+"  , 是否继续?","提示",{type:"warning"}).then(function(){Object(i.b)(s()({},{taskId:t})).then(function(t){e.$notify({title:"成功",message:"删除任务成功",type:"success",duration:2e3}),e.getList()}).catch(function(t){console.log(t)})}).catch(function(){e.$message.info("已取消删除!")})},handleDownload:function(){var t=this;this.downloadLoading=!0,Promise.all([a.e(23),a.e(22)]).then(a.bind(null,"zWO4")).then(function(e){var a=t.formatJson(["taskId","taskName","taskExeClass","taskExeMethod","taskCron","taskStatus","taskOnline"],t.list);e.export_json_to_excel({header:["ID","任务名称","任务执行类","任务执行方法","任务执行时间","是否启用","启动状态"],data:a,filename:"task-list"}),t.downloadLoading=!1})},formatJson:function(t,e){return e.map(function(e){return t.map(function(t){return"ID"===t?Object(l.c)(e[t]):e[t]})})}}},c={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{clearable:"",placeholder:"ID / 名称"},nativeOn:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key))return null;t.handleFilter(e)}},model:{value:t.listQuery.taskName,callback:function(e){t.$set(t.listQuery,"taskName",e)},expression:"listQuery.taskName"}}),t._v(" "),a("el-select",{staticClass:"filter-item",staticStyle:{width:"140px"},attrs:{clearable:"",placeholder:"是否启用"},on:{change:t.handleFilter},model:{value:t.listQuery.taskStatus,callback:function(e){t.$set(t.listQuery,"taskStatus",e)},expression:"listQuery.taskStatus"}},t._l(t.Status,function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),t._v(" "),a("el-select",{staticClass:"filter-item",staticStyle:{width:"140px"},attrs:{clearable:"",placeholder:"是否启动"},on:{change:t.handleFilter},model:{value:t.listQuery.taskOnline,callback:function(e){t.$set(t.listQuery,"taskOnline",e)},expression:"listQuery.taskOnline"}},t._l(t.Online,function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),t._v(" "),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-search"},on:{click:t.handleFilter}},[t._v(t._s(t.$t("table.search")))]),t._v(" "),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-refresh"},on:{click:t.handleCreate}},[t._v(t._s("刷新"))]),t._v(" "),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",loading:t.downloadLoading,icon:"el-icon-download"},on:{click:t.handleDownload}},[t._v(t._s(t.$t("table.export")))]),t._v(" "),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"success",icon:"el-icon-star-on"},on:{click:t.handleInit}},[t._v(t._s("初始化"))])],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading.body",value:t.listLoading,expression:"listLoading",modifiers:{body:!0}}],staticStyle:{width:"100%"},attrs:{data:t.list,border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"ID",width:"50"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.taskId))])]}}])}),t._v(" "),a("el-table-column",{attrs:{width:"auto",align:"center",label:"任务名称"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.taskName))])]}}])}),t._v(" "),a("el-table-column",{attrs:{width:"160px",align:"center",label:"任务执行类"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.taskExeClass))])]}}])}),t._v(" "),a("el-table-column",{attrs:{width:"130px",align:"center",label:"任务执行方法"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.taskExeMethod))])]}}])}),t._v(" "),a("el-table-column",{attrs:{width:"180px",align:"center",label:"任务执行时间"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.taskCron))])]}}])}),t._v(" "),a("el-table-column",{attrs:{width:"140",align:"center",label:"启用状态"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-switch",{staticClass:"switch",attrs:{"active-color":"#13ce66","inactive-color":"#ff4949","active-value":"1","inactive-value":"0","active-text":"ON","inactive-text":"OFF"},on:{change:function(a){t.changeTaskStatus(e.$index,e.row)}},model:{value:e.row.taskStatus,callback:function(a){t.$set(e.row,"taskStatus",a)},expression:"scope.row.taskStatus"}})]}}])}),t._v(" "),a("el-table-column",{attrs:{"class-name":"status-col",width:"80",align:"center",label:"启动状态"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-tag",{attrs:{type:t._f("statusFilter")(e.row.taskOnline)}},[t._v(t._s(0==e.row.taskOnline?" 否":"是"))])]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"270"},scopedSlots:t._u([{key:"default",fn:function(e){return["1"!=e.row.taskOnline?a("el-button",{attrs:{size:"small",type:"success",icon:"el-icon-success"},on:{click:function(a){t.handleModifyStatus(e.row,"1")}}},[t._v("Open")]):t._e(),t._v(" "),"0"!=e.row.taskOnline?a("el-button",{attrs:{size:"small",type:"info",icon:"el-icon-error"},on:{click:function(a){t.handleModifyStatus(e.row,"0")}}},[t._v("Close")]):t._e(),t._v(" "),a("el-button",{attrs:{type:"danger",size:"small",icon:"el-icon-delete"},on:{click:function(a){t.deleteById(e.row.taskId)}}},[t._v("Del")]),t._v(" "),a("router-link",{attrs:{to:"/task/editTask/"+e.row.taskId}},[a("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-edit"}},[t._v("Edit")])],1)]}}])})],1),t._v(" "),a("div",{staticClass:"pagination-container"},[a("el-pagination",{attrs:{background:"","current-page":t.listQuery.current,"page-sizes":[10,20,30,50],"page-size":t.listQuery.limit,layout:"total, sizes, prev, pager, next, jumper",total:t.total},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1)},staticRenderFns:[]};var r=a("VU/8")(o,c,!1,function(t){a("9Yp4")},"data-v-51d8e0d3",null);e.default=r.exports},oHgn:function(t,e,a){(t.exports=a("FZ+f")(!1)).push([t.i,"\n.edit-input[data-v-51d8e0d3] {\n  padding-right: 100px;\n}\n.cancel-btn[data-v-51d8e0d3] {\n  position: absolute;\n  right: 15px;\n  top: 10px;\n}\n",""])}});