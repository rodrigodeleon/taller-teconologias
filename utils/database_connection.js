var pg = require('pg');

module.exports = {
  connect: function () {
    var connectionString = process.env.DATABASE_URL ||  'postgres://dgvyrdhkydexfb:OxADObHgjNI_t_sBJ6CKdcKkw1@ec2-54-225-91-215.compute-1.amazonaws.com:5432/dctfujbpbd52ir'; //conexion para servidor local 'postgres://postgres:rodrigo1@localhost:5432/prueba';
    var client = new pg.Client(connectionString);
    client.connect();
    return client;
  },
 /* getNextId: function (client) {
    return client.query('select nextval(\'main_seq\')');
  }*/
};
