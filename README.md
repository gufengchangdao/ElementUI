# Swing仿饿了么组件库

项目网址：[ElementUI](https://github.com/gufengchangdao/ElementUI)

README文件飞书链接：[Swing仿饿了么组件库](https://riw8lxejdn.feishu.cn/docx/Yoszdo08qooCAJxOKlQcS4hJnTg)

## 前言

1. 该项目是仿造[饿了么组件库](https://element.eleme.cn/#/zh-CN)所开发的一套组件库，但不仅限于饿了么中出现的组件

2. 该项目可运行在java 1.8以及以上版本
3. 这是ElementUI的第一版，由于个人精力有限，时间也比较赶，组件部分方法可能存在问题，希望开发者能在GitHub上提交 Issues，我有时间会进行修改，也希望关于组件上有些想法的开发者也能和我分享宝贵建议。
4. 图标建议使用svg转换后代码使用，便于保证图标缩放后的质量，并且可以更改图标的颜色。因为建议配合UI设计工具使用(不建议使用即时设计，即时设计导出的SVG存在一些问题)，我用的是Figma，好用程度只能说平生所见。

### License

你可永久免费且自由地使用 ElementUI，如：用于研究、学习、甚至商业用途，但禁止在超越 License 约束内容的情况下用于商业用途等，请尊重知识产权。

### Contact

- 如有 bug 及建议等，请邮件至：1580313824@qq.com 或[提交 Issues](https://github.com/gufengchangdao/ElementUI)

## 开发指南

**所用依赖**

```Java
animated-transitions 便捷地创建动画
		timingframework 为大量组件提供平滑转换，过渡动画使用
		batik-all 解析svg
		swingx 提供一些功能强大的组件
		miglayout 功能强大的布局管理器
```

**代码中使用ElementUI**

由于动画需要提供一个默认的计时源，一般你的应用启动类这样写：

```Java
EventQueue.invokeLater(()->{
		// 创建计时源并启动
		SwingTimerTimingSource ts=new SwingTimerTimingSource();
		Animator.setDefaultTimingSource(ts);
		ts.init();

		//注册UI
		// FlatLightLaf.setup();

		//启动程序
		});
```

**目录结构**

```Plain
├─icon                                    未引入的处理好的图标类，不在项目中，需要时可以拖到项目中
│  ├─fill                                 填充图标
│  └─regular                              常规图标
├─libs
├─src
   ├─main
   │  ├─java
   │  │  └─com
   │  │      └─component
   │  │          ├─animator
   │  │          │  └─popup
   │  │          │      └─listener
   │  │          ├─basic
   │  │          │  ├─border
   │  │          │  ├─button
   │  │          │  ├─color
   │  │          │  ├─layout
   │  │          │  └─link
   │  │          ├─common
   │  │          │  ├─component
   │  │          │  └─template
   │  │          ├─data
   │  │          │  ├─badge
   │  │          │  ├─empty
   │  │          │  ├─label
   │  │          │  ├─pagination
   │  │          │  │  ├─model
   │  │          │  │  └─renderer
   │  │          │  ├─progress
   │  │          │  ├─result
   │  │          │  ├─skeleton
   │  │          │  ├─table
   │  │          │  │  └─renderer
   │  │          │  ├─tag
   │  │          │  └─tree
   │  │          │      └─checkboxtree
   │  │          ├─font
   │  │          ├─form
   │  │          │  ├─input
   │  │          │  │  ├─compound
   │  │          │  │  ├─model
   │  │          │  │  └─renderer
   │  │          │  ├─list
   │  │          │  │  └─renderer
   │  │          │  ├─picket
   │  │          │  ├─rate
   │  │          │  ├─select
   │  │          │  └─upload
   │  │          ├─navigation
   │  │          │  ├─breadcrumb
   │  │          │  ├─navmenu
   │  │          │  ├─pageheader
   │  │          │  ├─steps
   │  │          │  └─tabs
   │  │          ├─notice
   │  │          │  ├─alert
   │  │          │  ├─loading
   │  │          │  ├─message
   │  │          │  └─notification
   │  │          ├─others
   │  │          │  ├─card
   │  │          │  ├─carousel
   │  │          │  ├─collapse
   │  │          │  ├─line
   │  │          │  ├─popconfirm
   │  │          │  ├─popup
   │  │          │  ├─timeline
   │  │          │  └─tooltip
   │  │          ├─radiance               radiance项目中转换SVG的一部分代码
   │  │          ├─svg                    
   │  │          │  ├─app                 SVG转换简便的小应用
   │  │          │  ├─empty
   │  │          │  ├─icon                图标
   │  │          │  │  ├─fill                 
   │  │          │  │  └─regular
   │  │          │  └─upload
   │  │          └─util                   
   │  └─resources
   │      └─radiance
   │          └─tools
   │              └─svgtranscoder         SVG转换的模板
   │                  └─api
   │                      ├─java
   │                      └─kotlin
   └─test
       ├─java
       │  ├─com                            组件的测试
       │  ├─display                        文档中的运行预览
       │  └─lab                            一些有趣的代码
       └─resources                         测试用的资源
```

## 运行预览

- 演示所使用的美化包为**flatlaf**，项目中不要求使用该依赖
- 该示例只展示扩展的组件，其实swingx依赖中还有很多好用的组件


1. Basic
![](https://i.niupic.com/images/2022/10/26/a9Pt.png)
![](https://i.niupic.com/images/2022/10/26/a9Px.png)
![](https://i.niupic.com/images/2022/10/26/a9Ps.png)
![](https://i.niupic.com/images/2022/10/26/a9Py.png)

3. form

![](https://i.niupic.com/images/2022/10/26/a9Ph.png)
![](https://i.niupic.com/images/2022/10/26/a9Pz.png)
![](https://i.niupic.com/images/2022/10/26/a9Pu.png)
![](https://i.niupic.com/images/2022/10/26/a9PA.png)
![](https://i.niupic.com/images/2022/10/26/a9Pv.png)
![](https://i.niupic.com/images/2022/10/26/a9PB.png)




1. navigation

![](https://i.niupic.com/images/2022/10/26/a9Pi.png)

1. notice

![](https://i.niupic.com/images/2022/10/26/a9Pk.png)
![](https://i.niupic.com/images/2022/10/26/a9PC.png)
![](https://i.niupic.com/images/2022/10/26/a9Pj.png)

![](https://i.niupic.com/images/2022/10/26/a9PE.png)

![](https://i.niupic.com/images/2022/10/26/a9PD.png)


1. Data

![](https://i.niupic.com/images/2022/10/26/a9Pl.png)
![](https://i.niupic.com/images/2022/10/26/a9PF.png)
![](https://i.niupic.com/images/2022/10/26/a9Pm.png)


1. other

![](https://i.niupic.com/images/2022/10/26/a9PG.png)
![](https://i.niupic.com/images/2022/10/26/a9PL.png)
![](https://i.niupic.com/images/2022/10/26/a9Po.png)
![](https://i.niupic.com/images/2022/10/26/a9Pp.png)
![](https://i.niupic.com/images/2022/10/26/a9Pn.png)
![](https://i.niupic.com/images/2022/10/26/a9PM.png)
![](https://i.niupic.com/images/2022/10/26/a9PI.png)
![](https://i.niupic.com/images/2022/10/26/a9Pr.png)
![](https://i.niupic.com/images/2022/10/26/a9PH.png)
![](https://i.niupic.com/images/2022/10/26/a9PJ.png)
![](https://i.niupic.com/images/2022/10/26/a9Pq.png)
![](https://i.niupic.com/images/2022/10/26/a9PN.png)
![](https://i.niupic.com/images/2022/10/26/a9PO.png)

