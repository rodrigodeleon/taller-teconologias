var pg = require('pg');

module.exports = {
  connect: function () {
    var connectionString = process.env.DATABASE_URL || 'postgres://postgres:rodrigo1@localhost:5432/prueba';
    var client = new pg.Client(connectionString);
    client.connect();
    return client;
  },
 /* getNextId: function (client) {
    return client.query('select nextval(\'main_seq\')');
  }*/
};
