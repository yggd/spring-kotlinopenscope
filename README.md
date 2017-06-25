# Spring Boot & kotlin-allopenコンパイラプラグインのIDE追従について

Spring Initializrで言語をKotlinにすると、クラスや関数に対しコンパイル時に自動的にopenスコープをつけてくれるようになった。

https://kotlinlang.org/docs/reference/compiler-plugins.html

openスコープが指定されていない場合はJavaで言うところのfinal修飾子が付与されるのと同様であり、Spring AOP等でProxyが生成できなくなってしまう。  
このため、Spring BootでKotlinを扱う上ではほぼ必須のプラグインとなる。  
(都度openつけりゃいいじゃんという話もあるけど、Kotlin言語警察にMutable乱用罪で逮捕されるとか、フレームワークの使用有無で規約を変えていくのもなんだかなーという感じ)

さて、Maven/Gradleといったビルドツールではこのプラグインにより、kotlinで作成した関数が自動でopenスコープとなってくれるものの、通常開発・単体テスト時はIDE（統合開発環境）の力を借りることが多い。

そこでビルドツールのプラグインがIDEにどれくらい追従してくれるのかを調べてみた。
前提として、Spring Initializrから生成させたSpring AOPの単体テストが動作するMavenプロジェクトを用意する。（本プロジェクトがそれ）

## IntelliJ IDEA 2017.1.4
mavenプロジェクトをインポートしたら自動的にkotlin-allopenプラグインを認識してくれた。  
当たり前といえば当たり前だが、さすが(Kotlinの)本家JetBrains。

![IntelliJ IDEA all-open settings](https://raw.githubusercontent.com/yggd/imageupload/master/kotlin-allopen/IntelliJ.png)

Ultimate Editionなので画像の上ではSpringサポートがついているが、KotlinオプションはCommunity Editionでも使える（はず）。  
AOPをしかけたテストをIDE上で動かしても意図通りに動く。

![IntelliJ IDEA test](https://raw.githubusercontent.com/yggd/imageupload/master/kotlin-allopen/test-IntelliJ.png)

## SPRING TOOL SUITE 3.8.4(Eclipse) & Kotlin plugin 0.8.2

Pluginにも特に設定項目はなく、試験も失敗してしまう。  
(試験対象の関数以前に`SpringBootApplication`クラスがfinalだから、試験コンテキストの準備段階でダメという怒られ具合)

![STS test](https://raw.githubusercontent.com/yggd/imageupload/master/kotlin-allopen/test-STS.png)

m2eとKotlin plugin、中の人も違うしここら辺は今後も連携が難しいのかなぁ・・・まずKotlin plugin単体でall-openに対応できてない気がする。

## Netbeans

公式にKotlinのサポートされていないっぽいのでやってない。  
[ねこび〜ん](https://ja.netbeans.org/nekobean/)はかわいい。異論は認めない。

# 結論

* IntelliJの人は普通に使えるから気にするな。
* STSの人はたとえSpring BootがKotlinをサポートしているからといって、all-openをアテにしてはいけない。
* Netbeansの人はねこび〜ん好き。
