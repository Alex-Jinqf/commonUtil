//合并单元格
function uniteTable(tb,colLength,rowbegin,rowtotalflag){
    //检查表格是否规整
    if (!checkTable(tb,rowbegin,rowtotalflag))
        return;
        var i=0;
        var j=0;
        var rowCount = tb.rows.length - rowtotalflag;//行数
        var colCount = tb.rows[rowbegin].cells.length;//列数
        var obj1 = null;
        var obj2 = null;
        //为每个单元格命名
        for(i=rowbegin;i<rowCount;i++){
            for(j=0;j<colCount;j++){
                tb.rows[i].cells[j].id="tb_"+i.toString()+"_"+j.toString();
            }
        }

        //逐列检查合并
        for(i=0;i<colCount;i++){
            if(i==colLength)
            return;
            obj1=document.getElementById("tb_"+rowbegin+"_"+i.toString());

            for(j=rowbegin+1;j<rowCount;j++){
                ogj2=document.getElementById("tb_"+j.toString()+"_"+i.toString());
                if(obj1.innerText == obj2.innerText && (obj1.innerText != "无数据") && (obj1.innerText != "")){
                    obj1.rowSpan++;
                    obj2.parentNode.removeChild(obj2);
                }else{
                    obj = document.getElementById("tb_ "+j.toString()+"_"+i.toString());
                }
            }
        }
}


//检查表格是否规整
function checkTable(tb,rowbegin,rowtotalflag){
    if(tb.rows.length == 0) 
        return false;
    if(tb.rows[0].cells.length == 0)
        return false;
    for(var i=rowbegin;i<tb.rows.length-rowtotalflag-rowbegin;i++){
        if(tb.rows[rowbegin].cells.length != tb.rows[i].cells.length)
            return false;
    }
    return true;
}