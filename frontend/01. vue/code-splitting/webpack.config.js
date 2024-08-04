var path = require('path');
var MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = {
    /*webpack에서 3가지 기본 모드 -> production(배포), development(개발), none(테스트)*/
    mode: 'none',
    /*webpack 변환 파일 대상*/
    entry: './index.js',
    /*output dist 디렉토리를 경로로 잡아 파일명을 bundle.js로 변경*/
    /*output filename : bundle.js -> [chunkhash].js*/
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'dist')
    },
    /*loader 들을 추가 */
    module: {
        rules: [
            // 각각의 loader 규칙
            {
                test: /\.css$/,
                /**
                 * 순서의 영향이 있음
                 * ex)
                 *  use: ['css-loader', 'style-loader'] 에러
                 *  use: ['style-loader', 'css-loader'] 정상작동
                 *  css-loader -> css가 webpack으로 들어감
                 *  style-loader -> webpack으로 들어간 스타일을 inline style로 넣어줌
                 *  오른쪽에서 왼쪽으로 실행됨(css-loader (1), style-loader(2))
                 * */
                // use: ['style-loader', 'css-loader']
                use: [
                    {loader: MiniCssExtractPlugin.loader},
                    "css-loader"
                ]

            }
        ]
    },
    /**
     * 결과물에 대한 정보를 우리가 변경해서 사용할 수 있도록 처리함
     * */
    plugins: [
        new MiniCssExtractPlugin()
    ],
}