var gulp = require('gulp');
var sass = require('gulp-sass')(require('sass'));
var browserSync = require('browser-sync').create();

gulp.task('txt', function(){
    return console.log("aaaa");
})

gulp.task('sass', function(){
    return gulp.src('scss/*.scss')
    .pipe(sass())
    .pipe(gulp.dest('css/'))
    .pipe(browserSync.stream({match:'**/*.css'})); // broswer-sync로 전송 추가
})

gulp.task('browser-sync', function() {
    browserSync.init({
        server: {
            baseDir: "./"
        },
        startPath: "musictag.html"
    });
});

gulp.task('watch',['browser-sync'],function() {
    gulp.watch('scss/*.scss',['sass']);
    gulp.watch('*.html').on('change', browserSync.reload);
});
gulp.task('default',['watch']);