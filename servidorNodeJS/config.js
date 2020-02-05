var mysql = require('mysql');

module.exports = function(app) {

  // parse application/json
    app.use(bodyParser.json());
  // parse application/x-www-form-urlencoded
    app.use(bodyParser.urlencoded({ extended: false }));
  // parse the raw data
    app.use(bodyParser.raw());
  // parse text
    app.use(bodyParser.text());

  return app;
};

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: 'adat_zapatillas'
});

con.connect(function(err) {
  if (err) throw err;
  console.log("Conectado a la BBDD");
});