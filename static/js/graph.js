queue()
	.defer(d3.json,"127.0.0.1:5000/data")
	.await(MakeGraphs);

function MakeGraphs (error,DataJson) {
	window.alert(DataJson);
	var Itemnumber = DataJson;

	var ndx = crossfilter(Itemnumber);

	var Itemname = ndx.Itemnumber(function(d){return d["item"]});
	var ItemQty = ndx.Itemnumber(function(d){return d["qty"] });
	
	window.alert("Insied the javascript")
	window.alert(ItemQty);

	var all = ndx.groupAll();
	var numitemname = Itemname.group();
	var numitemqty = ItemQty.group();

	var totalitemqty = ndx.groupAll().reduceSum(function(d){
		return d["qty"];
	});

	var gItemname = dc.rowChart("#resoruce-type-row-chart");
	var gItemQty = dc.numberDisplay("#qty");
	var gTotalQty = dc.numberDisplay("#total_qty");

	gItemQty.formatNumber(d3.format("d"))
			.valueAccessor(function(d){return d;})
			.group(qty)
			.formatNumber(d3.format(".3s"));

	gTotalQty.formatNumber(d3.format("d"))
			.valueAccessor(function(d){return d;})
			.group(totalitemqty)
			.formatNumber(d3.format(".3s"));

	gItemname.width(300)
			.height(250)
			.dimension(Itemname)
			.group(numitemname)
			.xAxis().ticks(4);

	dc.renderAll();
}
