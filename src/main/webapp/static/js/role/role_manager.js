let em = new Vue({
    el: '#main-container',
    data: function () {
        return {
            Role: {},
            setting: {
                data: {
                    key: {
                        title: ''
                    },
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback: {},
                view: {},
                nodes: []
            }
        }
    },
    methods: {
        initTree: function () {
            let treeObject = $.fn.zTree.init($("#treeOffice"), this.setting, this.nodes);
        }
    },
    created: function () {
        this.Role = parent.layer.Role;
        this.nodes = parent.layer.roleList;
        console.log(this.Role);
        console.log(parent.layer.roleList);
    }
})