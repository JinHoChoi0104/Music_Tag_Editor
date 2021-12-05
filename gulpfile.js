var gulp = require('gulp');
var sass = require('gulp-sass')(require('sass'));

gulp.task('txt', function(){
    return console.log("aaaa");
})

gulp.task('sass', function(){
    return gulp.src('Front/scss/*.scss')
    .pipe(sass())
    .pipe(gulp.dest('Front/css/'));
})
