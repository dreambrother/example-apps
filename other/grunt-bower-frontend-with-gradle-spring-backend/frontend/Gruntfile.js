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
                    'build/app.min.js': ['js/src/app.js', 'lib/angular/angular.js']
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
        }
    });

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-jasmine');
    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-watch');

    // Default task(s).
    grunt.registerTask('default', ['bower', 'package']);
    grunt.registerTask('package', ['jasmine', 'uglify', 'less']);
    grunt.registerTask('dev', ['less', 'watch']);
};
