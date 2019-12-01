let em = new Vue({
    el: '#main-container',
    data: function () {
        return {
            map: {
                offId: '',/*查询dxUser和yxUser的office ID*/
                roleId: '',/*查询dxUser和yxUser的role ID*/
                insertUserId: [],/*批量插入权限的userId*/
                removeUserId: [],/*批量移除权限的userId*/
            },
            dxUser: {},/*待选user*/
            yxUser: {},/*已选user*/


            haveUserClass: 'hidden',
            notUserClass: 'hidden',
            setting: {
                data: {
                    key: {
                        title: ""
                    },
                    simpleData: {
                        enable: true,
                        /*   pIdKey: 'parentId'*/
                    }
                },
                callback: {onClick: this.TreeClick},
                view: {fontCss: this.changeColor}
            },
            nodes: [],  /*公司节点*/
            treeObj: {},/*树对象*/
            name: "",/*节点名字*/

        }
    },
    methods: {
        initTree: function () {

            let treeObject = $.fn.zTree.init($("#treeOffice"), this.setting, this.nodes);
            this.treeObj = treeObject;

        },
        TreeClick: function (event, treeId, treeNode) {
            this.searchClear();
            this.map.offId = treeNode.id;
            console.log(this.map)
            this.notRoleUser();
            this.haveRoleUser();


        },
        searchClear: function () {

            let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());

            for (let index in nodeArr) {
                nodeArr[index].higtLine = false;
                this.treeObj.updateNode(nodeArr[index]);//更新节点，自动调用清除css
            }
        },
        search: function () {

            console.log("bbbb")
            let node = this.treeObj.getNodesByParamFuzzy("name", this.name, null)

            let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());
            // let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());

            // this.map.officeName = this.name;
            for (let index in nodeArr) {
                for (let nodeIndex in node) {
                    if (nodeArr[index].id == node[nodeIndex].id) {
                        nodeArr[index].higtLine = true;//设置高亮标记
                        //更新节点  会触发自动的设置css等回调
                        this.treeObj.updateNode(nodeArr[index])

                    }
                }
            }

        },
        changeColor: function (treeId, treeNode) {
            return treeNode.higtLine ? {color: "red"} : {color: ''}
        },

        notRoleUser: function () {//根据当前角色id，查询出相应role的user，
            axios({
                url: 'manager/sysuser/selectNotRole',
                method: 'post',
                data: this.map
            }).then(response => {
                this.dxUser = response.data;
                //给每个用户绑定新属性show ,用于控制被选中与否
                for (let i = 0; i < this.dxUser.length; i++) {
                    this.dxUser[i].show = false;
                }

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        haveRoleUser: function () {//根据当前角色id，查询出相应role的user，
            axios({
                url: 'manager/sysuser/selectHaveRole',
                method: 'post',
                data: this.map
            }).then(response => {
                this.yxUser = response.data;
                //给每个用户绑定新属性show ,用于控制被选中与否
                for (let i = 0; i < this.dxUser.length; i++) {
                    this.yxUser[i].show = false;
                }

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        selectToRemove: function (id) {//改动被选中的赋值
            this.map.removeUserId = [];
            this.haveUserClass = 'hidden'
            for (let i = 0; i < this.yxUser.length; i++) {
                if (this.yxUser[i].id == id) {
                    this.yxUser[i].show = !this.yxUser[i].show;
                }
                if (this.yxUser[i].show == true) {
                    this.map.removeUserId.push(this.yxUser[i].id);//将找到的需要操作的人员的id放入uids中
                }
            }
            console.log(this.map.removeUserId);
            if (this.map.removeUserId.length > 0) {
                this.haveUserClass = 'show';
            }


        },
        selectToInsert: function (id) {//改动被选中的赋值
            this.map.insertUserId = [];
            this.notUserClass = 'hidden'
            for (let i = 0; i < this.dxUser.length; i++) {
                if (this.dxUser[i].id == id) {
                    this.dxUser[i].show = !this.dxUser[i].show;
                }
                if (this.dxUser[i].show == true) {
                    this.map.insertUserId.push(this.dxUser[i].id);//将找到的需要操作的人员的id放入uids中
                }
            }
            console.log(this.map.insertUserId);
            if (this.map.insertUserId.length > 0) {
                this.notUserClass = 'show';
            }
        },
        insertBatch: function () {
            console.log(this.map)
            axios({
                url: 'manager/role/insertBatch',
                method: 'post',
                data: this.map
            }).then(response => {
                console.log(response.data)
            }).catch(function (error) {
                layer.msg(error);
            })
        },

        removeBatch: function () {
            axios({
                url: 'manager/role/deleteBatch',
                method: 'post',
                data: this.map
            }).then(response => {
                console.log(response.data)
            }).catch(function (error) {
                layer.msg(error);
            })
        }

    },

    created: function () {
        this.nodes = parent.layer.role_user;
        this.insertRoleId = parent.layer.roleId;
        this.map.roleId = parent.layer.roleId;
        this.haveRoleUser();

    },
    mounted: function () {
        this.initTree();
    }


})