var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    mode: 'none',
    entry: './index.js',
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'dist'),
    },
    /* webpack document를 이용해 특정 속성들을 확인해서 사용 필요 */
    devServer: {
        port: 9000,
    },
    plugins: [
        new HtmlWebpackPlugin({
            // index.html 템플릿을 기반으로 빌드 결과물을 추가해줌
            template: 'index.html',
        }),
    ],
};