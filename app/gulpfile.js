'use strict';

const gulp = require('gulp');
const scss = require('gulp-scss');
const sourcemaps = require('gulp-sourcemaps');
const del = require('del')
const sequence = require('gulp-sequence');
const browserSync = require('browser-sync').create();
const notify = require('gulp-notify');
const multipipe = require('multipipe');
const eslint = require('gulp-eslint');
const through2 = require('through2').obj;
const fs = require('fs');
const concat = require('gulp-concat');
const browserify = require('gulp-browserify');

gulp.task('serve', function() {
    browserSync.init({
        server: 'public'
    });
    browserSync.watch('public/**/*.*', browserSync.reload);
});

gulp.task('styles', function() {
    return multipipe(
        gulp.src('client/stylesheet/main.scss'),
        sourcemaps.init(),
        scss(),
        sourcemaps.write('.'),
        gulp.dest('public/stylesheet')
    ).on('error', notify.onError(function(err){
            return {
                title: "Styles",
                message: err.message
            };
    }));       
});

gulp.task('assets', function() {
    return gulp.src('client/assets/**')
        .pipe(gulp.dest('public/assets'));
});

gulp.task('js', function() {
    return gulp.src('client/js/**/*.js')
        .pipe(gulp.dest('public/js'));
});

gulp.task('index', function() {
     return gulp.src('client/index.html')
        .pipe(gulp.dest('public'));
});

gulp.task('templates', function() {
     return gulp.src('client/templates/**/*.html')
        .pipe(gulp.dest('public/templates'));
});

gulp.task('clean', function() {
    return del.sync(['public/js', 'public/stylesheet', 'public/templates', 'public/index.html', '!public/bower_components/**']);
});

gulp.task('build', sequence('clean', ['styles', 'assets', 'index', 'js', 'templates']));

gulp.task('watch', function() {
    gulp.watch('client/stylesheet/**/*.*', ['styles']);
    gulp.watch('client/assets/**/*.*', ['assets']);
    gulp.watch('client/index.html', ['index']);
    gulp.watch('client/templates/**/*.html', ['templates']);
    gulp.watch('client/js/**/*.*', ['js']);
});

gulp.task('default', sequence('build', ['watch', 'serve']));

gulp.task('lint', function() {

    let eslintResults = {};
    let cacheFilePath = process.cwd() + '/client/tmp/lintCache.json';

    try {
        eslintResults = JSON.parse(fs.readFileSync(cacheFilePath));
    } catch(e) {

    }
    
    return gulp.src('client/js/**/*.js')
        .pipe(eslint())
        .pipe(through2(function(file, enc, callback) {
            eslintResults[file.path] = {
                eslint: file.eslint,
                mtime: file.stat.mtime
            };
            callback(null, file)
        }, function(callback) {
            fs.writeFileSync(cacheFilePath, JSON.stringify(eslintResults));
            callback();
        }))
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
});