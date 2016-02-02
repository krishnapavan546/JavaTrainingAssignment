var req=require('request');
var async=require('async');

exports.handler=function(request,response){
  async.parallel([
    function(callback){
      var productId=request.param('productId');
      console.log(`productId`);
      var url="http://localhost:8080/products/product/"+productId
      req(url,function(err,response,body){
      if(err){console.log(err);callback(true);return;}
      console.log(`Product::Inside body presentation ${body}`);
      obj=JSON.parse(body);
      //res.end( JSON.stringify(obj));
      callback(false,obj)
      });
    },
    function(callback){
      var productId=request.param('productId');
      console.log(productId);
      var url="http://localhost:8085/price/"+productId;
      req(url,function(err,response,body){if(err){console.log(err);callback(true);return;}
      console.log(`Product::Inside body presentation ${body}`);
      obj=JSON.parse(body);
      callback(false,obj)});
    }
  ],
  function(err,results)
  {
    if(err){console.log(err);response.send(500,"ServerError");return;}
    completeResult={};
    completeResult.productId=results[0].productId;
    completeResult.product=results[0].product;
    completeResult.price=results[1].price;
    response.send(completeResult);
});
};
