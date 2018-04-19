const express = require('express');
const logger = require('morgan');
const soap = require('soap');
const app = express();
const http = require('http');   

app.use(express.urlencoded({extended:false}));
app.use(express.json());

var url = 'http://localhost:8085/test?wsdl';


app.get('/add/:cmd/:cmd2', (req, res) => {
  var args = {arg0: req.params.cmd, arg1 :req.params.cmd2};
  soap.createClient(url, function(err, client) {
  	 client.add(args, function(err, result) {
       console.log(result)
     });
  });
  res.send('works');
});

 
app.get('/show/:cmd/:cmd2', (req, res) => {
  var args = {arg0: req.params.cmd, arg1 :req.params.cmd2};
  var url = `http://localhost:8080/WebService?email=${req.params.cmd}&password=${req.params.cmd2}`;
  var req = http.request(url,res=> {
    res.on('data',(chunk)=> {
      console.log(`BODY: ${chunk}`);
    });
  });
  req.end();
  res.send('works');
});
  

app.listen(8084);