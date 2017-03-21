'use strict';

const gulp = require('gulp');
const scss = require('gulp-scss');
const sourcemaps = require('gulp-sourcemaps');
const del = require('del')
const sequence = require('gulp-sequence');
const browserSync = require('browser-sync').create();
const notify = require('gulp-notify');
const multipipe = require('multipipe');

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

gulp.task('index', function() {
     return gulp.src('client/index.html')
        .pipe(gulp.dest('public'));
});

gulp.task('clean', function() {
    return del('public/');
});

gulp.task('build', sequence('clean', ['styles', 'assets', 'index']));

gulp.task('watch', function() {
    gulp.watch('client/stylesheet/**.*', ['styles']);
    gulp.watch('client/assets/**.*', ['assets']);
    gulp.watch('client/index.html', ['index']);
});

gulp.task('default', sequence('build', ['watch', 'serve']));