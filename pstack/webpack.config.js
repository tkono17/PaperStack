const path = require('path')

module.exports = {
  mode: 'development', 
  entry: path.join(__dirname, 'src/pstack.js'), 
  output: {
    path: path.join(__dirname, 'dest'), 
    filename: 'pstack.js'
  },
  module: {
    rules: [
      {
	test: /\.js$/,
	exclude: /node_modules/,
	loader: 'babel-loader', 
	options: {
	  presets: [ '@babel/preset-env', '@babel/preset-react' ]
	}
      }
    ]
  }
}
