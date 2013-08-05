module.exports = function(grunt) {

    grunt.initConfig({
            pkg: grunt.file.readJSON('package.json'),
            uglify: {
                  build: {
                        src: 'src/<%= pkg.name %>.js',
                        dest: 'build/<%= pkg.name %>.min.js'
                  }
            },
            jasmine: {
                test: {
                    src: 'src/*.js',
                    options: {
                        specs: 'spec/*spec.js'
                    }
                }
        }
    });

  // Load the plugin that provides the "uglify" task.
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-jasmine');

  // Default task(s).
  grunt.registerTask('default', ['jasmine', 'uglify']);

};