var mysql = require('mysql');
const express = require('express'),
      router = express.Router(),
      server = express();
      
const con = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'adat_zapatillas'
});

con.query('SELECT * FROM zapatillas', (err,rows) => {
  if(err) throw err;

  console.log('Data received from Db:\n');
  console.log(rows);
  res.send(rows);
});

server.use(router);
server.listen(8000);