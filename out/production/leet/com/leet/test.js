var data = [{list:[1,2,3],total:50},{list:[11,22,33],total:60},{list:[111,222,333],total:70},{list:[1111,2222,3333],total:80}];

var lists = [];
var totals = 0;

data.reduce(getLists,0)
for(i =0;i<data.length;i++){

    lists.concat(data[i].list)
    // for(j = 0;j<data[i].list.length;j++)
    //     lists.unshift(data[i].list[j])
    totals = totals + data[i].total
}

function getLists(lists,data) {
    for(i = 0;i<data.length;i++){
        lists = lists.concat(data[i].list)
    }
    return lists;
}

// for(data1 in data){
//     lists.unshift(data1.list)
//     totals = totals + data1.total
// }
var object = {lists,totals}

console.log(object);
