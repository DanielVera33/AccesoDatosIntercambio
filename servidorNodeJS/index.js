var mysql = require('mysql');
const bodyParser = require('body-parser');
const express = require('express'),
      router = express.Router(),
      server = express();

const con = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'adat_zapatillas'
});

server.use(bodyParser.json());
server.use(bodyParser.urlencoded({ extended: false }));
// Leer una zapatilla
router.route('/leeZapatillas')
    .get(function (req, res) {
      con.query('SELECT * FROM zapatillas', (err,rows) => {
        if(err) throw err;
      
        console.log('Data received from Db:\n');
        console.log(rows);
        res.send(rows);
      });
});
// Crear una zapatilla nueva
router.route('/escribirZapatilla')
    .post(function (req, res) {
      con.query(`INSERT INTO zapatillas (nombre) values ('${req.body.nombre}')`, (err,rows) => {
        if(err) throw err;
      
        console.log('Data received from Db:\n');
        console.log(rows);
        res.send(rows);
      });
});
// Modificar una zapatilla
router.route('/updateZapatilla')
    .put(function (req, res) {
  con.query(`UPDATE zapatillas SET nombre=? where id=?`, [req.body.nombre,req.body.id], function (error, results, fields) {
   if (error) throw error;
   res.end(JSON.stringify(results));
 });
});
// Delete una zapatilla
router.route('/borrarZapatilla')
    .put(function (req, res) {
  con.query(`DELETE zapatillas`, [req.body.nombre,req.body.id], function (error, results, fields) {
   if (error) throw error;
 });
});

server.use(router);
server.use(bodyParser.json());
server.use(bodyParser.urlencoded({ extended: false }));
server.listen(8000);
console.log("El servidor está inicializado en el puerto 8000");