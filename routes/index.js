var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) { //eslint-disable-line no-unused-vars
    res.render('index', { title: 'Express', });
});

module.exports = router;
