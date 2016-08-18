var express = require('express');
var router = express.Router();

router.get('/', function(req, res, next) { //eslint-disable-line no-unused-vars
    res.send('la prueba funca');
});

module.exports = router;
