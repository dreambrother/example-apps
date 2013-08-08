module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        bower: {
            install: {
                //just run 'grunt bower:install' and you'll see files from your Bower packages in lib directory
            }
        },
        uglify: {
            build: {
                files: {
                    'build/app.min.js': ['lib/angular/angular.js', 'js/src/app.js']
                }
            }
        },
        jasmine: {
            test: {
                src: 'js/src/*.js',
                options: {
                    specs: 'js/spec/*Spec.js'
                }
            }
        },
        less: {
            compile: {
                src: 'less/styles.less',
                dest: 'build/styles.css'
            }
        },
        watch: {
            styles: {
                files: ['less/*.less'],
                tasks: ['less']
            }
        },
        env: {
            dev: {
                scripts: 'list'
            },
            build: {
                scripts: 'bundle'
            }
        },
        preprocess: {
            html : {
                src : 'html/index.html',
                dest : 'build/index.html'
            }
        }
    });

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-jasmine');
    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-env');
    grunt.loadNpmTasks('grunt-preprocess');

    // Default task(s).
    grunt.registerTask('default', ['bower', 'package']);
    grunt.registerTask('package', ['jasmine', 'uglify', 'less']);
    grunt.registerTask('dev', ['env:dev', 'preprocess', 'less', 'watch']);
    grunt.registerTask('build', ['env:build', 'preprocess', 'package'])
};
