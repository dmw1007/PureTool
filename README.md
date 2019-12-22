# PureTool
我开发的第一款APP

## 界面展示

[视频演示地址](https://www.bilibili.com/video/av80231325/)

<img src="https://gitee.com/CodeXtreme/Image/raw/master/PureTool/shots/Pure_Logo.png"  height="350" width="350"> <br>

<img src="https://gitee.com/CodeXtreme/Image/raw/master/PureTool/ping.png" width="35%">

<img src="https://gitee.com/CodeXtreme/Image/raw/master/PureTool/shots/home.jpg" width="38%">
<img src="https://gitee.com/CodeXtreme/Image/raw/master/PureTool/shots/page.png" width="35%">
<img src="https://gitee.com/CodeXtreme/Image/raw/master/PureTool/shots/tools.png" width="35%">
<img src="https://gitee.com/CodeXtreme/Image/raw/master/PureTool/shots/ziliao.png" width="35%">
<img src="https://gitee.com/CodeXtreme/Image/raw/master/PureTool/shots/jinzhi.png" width="35%">
<img src="https://gitee.com/CodeXtreme/Image/raw/master/PureTool/shots/my.png" width="35%">

# 一、开发理念

学习是现如今每个人都必须做的一件事，针对学生群体而言，学习就是他们生活的主
要部分，因此 Pure 工具箱被我们开发出来，用来帮助学生群体的学习和生活。Pure 工具
箱主要分成两大功能——学习资料库和工具箱。资料库内包含学习资料以及试卷用以用户
查阅参考，同时引入了腾讯 X5 浏览器内核来提升使用体验。工具箱内包含了许多实用小工
具，诸如翻译、进制转换、文字识别等，帮助用户解决一些小问题。


# 二、 界面设计

>    activity_main 的界面布局设计主要通过组件化的 include 方式实现，主布局方式为DrawerLayout，然后 include 入 app_bar_main 和 NavigationView 组件，其中的NavigationView 组件用来设置侧滑栏的 menu 选项，app_bar_main 组件中使用了AppBarLayout 的布局方式来实现 Toolbar 工具顶栏，这样就实现了侧滑栏的交互设计。随后在 app_bar_main 中 include 入 content_main 界面布局，content_main 中则包含了软件首界面的内容，以 WebView 的方式呈现。这样组件化布局的方式可以使界面布局形式丰富，同时各组件之间不会相互干扰，便于管理界面布局。

>之后是侧滑栏 menu 中的工具箱选项的界面布局，主要使用最外层 LinearLayout+内层线性布局+Button 的布局方式，内层线性布局使 Button 分为两列，并设置 weight 属性使Button 分布均匀。为使整体界面美观，内层的 Button 使用了 ImageButton，并且使用Sketch 绘图软件绘制出了美观的按钮图片。但这种设计方式也给屏幕适配带来了困难，由于 ImageButton 的大小需要按图片比例来写出具体大小，这就造成在不同比例的手机上会使 ImageButton 有不同的缩放情况，为解决这个问题，我们测试了市面上 16:9 18:9 18.5:9这三种屏幕比例的显示情况，最后确定了一个较为合适的数值。
 
 # 三、功能设计

 软件的大部分功能主要得益于百度 AI 开放平台和腾讯浏览服务的开放服务。百度 AI
提供了翻译，OCR 识别，识图，语音识别等技术并提供 API 和 SDK 文档方便用户接入，
Pure 工具箱主要使用了其中的百度翻译和 OCR 文字识别技术。软件的 WebView 组件引入了腾讯浏览服务，缩写为 TBS，依托 X5 内核的强大能力，软件的网页浏览体验得到了提升。

# 四、项目总结

* 该 APP 的工程文件创建于 18 年 11 月份，在此之前我们团队学习了一个多月的
Andriod 开发，期间看了很多网上的开源代码，那时候对软件的开发还是一头雾水，也没
有明确的发展方向，在分析了身边大学生学习的一些痛点后我们决定开发出此软件来给大
学生群体的学习与生活带来便利。
* Pure 工具箱开发的主要难点就是每制作出一个小工具就相当于在开发一个新的 APP，需
要不断去学习和研究 Andriod 的其他知识，但这个难点也让我们渐渐掌握了 Andriod 开发
的技巧，与刚起步时相比，现在的我们对 Andriod 开发可谓得心应手了很多，对软件开发
也有了自己的心得
*  Pure 工具箱会在未来继续不断的完善去增加更多有趣有料的小功能，丰富资料库资源，
开放资料库上传功能，改版资料库网站，使其逐渐发展为一个大型资料共享站。 
