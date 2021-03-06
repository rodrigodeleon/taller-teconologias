var express = require('express');
var path = require('path');
var favicon = require('serve-favicon'); //eslint-disable-line no-unused-vars
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

var routes = require('./routes/index');
var users = require('./routes/users');
var apigetUsers = require('./routes/api/users/getUsuario');
var apiPointsCheck = require('./routes/api/points/check');
var apiPointsGetPuntos = require('./routes/api/points/getPuntos');
var usersScores= require('./routes/api/users/scores');
var prueba = require('./prueba');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false,}));
app.use(cookieParser());
app.use(require('less-middleware')(path.join(__dirname, 'public')));
app.use(express.static(path.join(__dirname, 'public')));

app.use('/prueba', prueba);
app.use('/', routes);
app.use('/users', users);
app.use('/api/users/scores', usersScores);
app.use('/api/points/check', apiPointsCheck);
app.use('/api/points/getPuntos', apiPointsGetPuntos);
app.use('/api/users/getUsuario',apigetUsers)

// catch 404 and forward to error handler
app.use(function (req, res, next) { //eslint-disable-line no-unused-vars
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function (err, req, res, next) { //eslint-disable-line no-unused-vars
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err,
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function (err, req, res, next) { //eslint-disable-line no-unused-vars
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {},
  });
});

module.exports = app;
