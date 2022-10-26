# Swing仿饿了么组件库

项目网址：[ElementUI](https://github.com/gufengchangdao/ElementUI)

README文件飞书链接：[Swing仿饿了么组件库](https://riw8lxejdn.feishu.cn/docx/Yoszdo08qooCAJxOKlQcS4hJnTg)

## 前言

1. 该项目是仿造[饿了么组件库](https://element.eleme.cn/#/zh-CN)所开发的一套组件库，但不仅限于饿了么中出现的组件

2. 该项目可运行在java 1.8以及以上版本
3. 这是ElementUI的第一版，由于个人精力有限，时间也比较赶，组件部分方法可能存在问题，希望开发者能在GitHub上提交 Issues，我有时间会进行修改，也希望关于组件上有些想法的开发者也能和我分享宝贵建议。

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

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=M2IwYzg3ZGViYTEwNzUzZTQ5OTcyNzkzOTQ1YjlhMjFfMzJ3ZVhodHZQQUZCd3V2WFoxOEVlWGpVUWNEZTFSR0VfVG9rZW46Ym94Y25KZU1TdGV0MWVSbUlTdm5IT2ZMbjJkXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=NjcxMjgyODcyNjU2YTAwZDU2MGZjOWFmMmNiYjljZDZfc1RoeDdjd2poNFFQTlk0UmNoQkQ2VWl6M3lpcWl3MmJfVG9rZW46Ym94Y250YVhXTGlkbVlna0JoNVllMm04RXlmXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=OGNmNGRhMWY5MTFhM2RhMDA3ZTg3YzY3YWNkMzZiNWFfWmdPanR2b24wTjNuY1FmeWhLa0lkZDlISTVkMGdNUkRfVG9rZW46Ym94Y25tWVJEaUNDM1VRYVhSVW5HM3BMRm1mXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=MjFkYmE1YTM1NTNlNzUxNGM2MjNiNDVhMmNjNjhjNTdfNUFvOEtJNVFrY2tUQnloVk1rVTZhM2ZVT2lsdTVpNWpfVG9rZW46Ym94Y25ldmREVHNRUVZQMVd4ajhmVkZ5M3RmXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

1. form

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWNiNmQwNDA5ZmNiOGFkN2YwZDljNWVhYmU5ZTEzMjlfbjNyeDdwb2FzQ1RDdzd5UjVQekNiMFhZMmNUbGtPaGFfVG9rZW46Ym94Y25HcXJsN3RIQ0pXQnJKUU8yWENwcmJiXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YzM2NDJhODk5ZTgxODBkMGE0ZmUyMmI3YWU5NzU5NmJfdjlvWFhZWHBXMHgxQ1kzbGhvZUZGb1NjUTB2bjNqTGJfVG9rZW46Ym94Y25CRlVrcTlDM011eE5KcmlsRDZCTXZjXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YmQxMzY1MjI1YzkxYjczYzFiZjkwMDUzYzU3NmUwMjFfcVh5WUdDVDJBMmxpdmg5TUJFc2ZKaFByZG5rNEo5RUJfVG9rZW46Ym94Y245THIza2hLaXpkY1ltd0JPUGZRanhjXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=OWYxMTFjMjdhYmEzMmU5ZDY2YTNhOTI4ZjAzNzE1NzlfcVJtYzhOMXdmNlNDVmJaY3RVcVdVVGhuT2djZnNmdUhfVG9rZW46Ym94Y25Jd0t2Z2FlSWRnb1ViZ0g1RFBHTmRjXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=NjFlODQzOWYyMDVhODQ0OTlkNTdhYWVhYzM1NTY4OTZfcG1nOHBoajVNN29tbDNIbEZ3ckJnZnRBajh5YVFJTTZfVG9rZW46Ym94Y25KS3hXU0lvU3k5RFQ1MmxFVW1nWW9oXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YjU2MTY3NzhhZjFhM2IxYWIyZmQzOTlhNzU4MDAxMDdfRFB6NHpDMWl2VndNUGNUUVdUZ2FLY056TUlxQ005d2dfVG9rZW46Ym94Y25jZ0M2cXRCaVRWMHoyMUpUWFJ0VGVjXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

1. navigation

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YjBiZDA3MzI3YjJmMjlmOTVjNjAyZDE4YjhkOGY1MDJfZkNDVFdzQjNIWTlIMWtDTUZONWpldDE2WEp6UmY0S0VfVG9rZW46Ym94Y250QVA1S3BiTlRBdXpjME1zVnZuVTllXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

1. notice

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=NzU0MTYxNDNlMzhmYTJhYTExODViOTczZmIzNDdmOWJfUGw3a1dMMDBQZGw3N2tTQkFkUkY3WEhyd2ZmQ1QxQ2tfVG9rZW46Ym94Y25UaW1FcWRnZ1ZFcUJ4bFBndzEzRlZjXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YTk1MTFlNzViODgxNzY5NjM2MzIwZTM1YzVlNzI3ZDRfbFdvUTdxSDVQRExUVWZNR3pESEdNdUtYQzE3em1DU2tfVG9rZW46Ym94Y25VS2l1YnpDUE1MaU1PbGsxOVFkcGJlXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YmE0MzI2OGY1MmNlZDE0YjEyYzdjMzE2OTI3ZDYwNzRfUHVLdE9GV0hBUWx0OEJzT1pvSTRtVVAyYlZ3eEloaDlfVG9rZW46Ym94Y24wcnBGTTR4a29McHpTYTdLZkJ5MkJjXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YmI3MmE3ODUzMzMzZjczOTBjYTgyYTQ4ZTQxYzg1ZmVfWmlyemZXb05oVmJKVHY1YURzdjNOa3ZibTgzdjFuT2dfVG9rZW46Ym94Y25GVnFkUUVZa2Q5VWZ1bEtMTzA1eGtlXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=NTY2YmU1OTlhNWEwYjY5M2YxYzIzZDAyYzMxNWZhM2FfbW9ZQ3ZnZlZ1cFBrV0JZWUh5b0JWem5wcUJHTHVjckNfVG9rZW46Ym94Y25MN0l1WGM4RjU5bmQwbXkwTk9OWXRlXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

1. Data

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YmQzNzJmZDQwNDZkYTYwZjg1ZjZhMTEzN2FmZmJiOTFfRlFDOTE4ckplOHR4ZGQ2YjVqSnZnbmhOZ0hFTHN0S09fVG9rZW46Ym94Y25XRWFFTVZGU0RHZTZweHlEWkdkUDhlXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=MGU5NDExMWM1Y2YxMTE5MDczMmE1ZDg5N2RlOGQ5Y2NfYjZVN0ZyTkJMTmh3SHNUb3NlUHRmZVlvVVR6VXlnUUdfVG9rZW46Ym94Y25mVG5ta3VPMTZVc2cybEkxTVBvSUVkXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YTQ3ZDA3NjliNTk2YjNhNWI4MjU0NWM3MzhhOTM3MzZfNjVWM1FRQjhVM05oOXR4UlNkUTVvdllaS1R1RzhPT01fVG9rZW46Ym94Y25ESjNBdkpVY0loZ1MzbUg3REUwTWhiXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

1. other

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=N2FkMmJjZTgwMzI1YTk1YWE0NDY5ZTE4MzFlNjQwMTRfVjRGMEpiZG1FQmFKdTE5NE5Tc3kwN3dNcllReHJSNnpfVG9rZW46Ym94Y25zQ0xKTUYzYXBJZW9lM1lTZHVMT05nXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDNjNWU5ZDczYTQ2NDczZTQ1ZjgzODZkOTBkY2I1MjlfZk81UktPcHlDT0xOOGFQSU5BQWxzR2NSODhXcENETFlfVG9rZW46Ym94Y240akFNWGNhOHgxNnJ0UUdMYWhwTHNoXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjI0OGI0M2I3YzQyYjk1YzYzMjU1MTY5NjUxMGZkY2VfWTdDQU5LeTcySzdrMlMzSTdCRHFhMnNxbjNWUjdxc2ZfVG9rZW46Ym94Y24xUjRMbldEZVlMSFJ6U0tEMDA2Um1iXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YjBlY2E5MTI2ZDVjMWZjY2M2MjE3YjUwMjQwOWZiMjlfZ3czSHJrdDBKNFloNjRyMTZPSjdVM0VaZnh4blJNMkFfVG9rZW46Ym94Y25WRG95ZFZtcHo4RkRnWVd5dWs1cEtkXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=NTE1ZTVhMDE3MWYxYzk3YTBhOTMxZGE4ZDEwM2NiZTdfMXRNT3p5dGZVYTRVTkhPbnlEQTBDaHd6QTlpRTgxa2tfVG9rZW46Ym94Y25iWkR6MHpUNU85TFJZOTBma09XYWZiXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=OTQ4Y2U1YzllNzM0YWIyZTFiZDNhYjRhMzgyZjczODJfTTU2YlpoTTQ0TmJtNEdqdkJDYzlOZk5GSlFJY2xXNmhfVG9rZW46Ym94Y25ZT1VjRkE3bTUyVld5VEJ4c2J5T2JiXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=NDliY2I2NzdmZjM1NWZmM2FiNjc1OGNhMDMyNGEzMjVfVHdHbjFUT3hKcGV0ZVg4bHc0UVRlRVdNYkdva3FkOWhfVG9rZW46Ym94Y25Sc2JLNUQzNVFMTTRPTDJHeDlSeFlnXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=NTIwOTAwZGY0NDdlZTY1YzUyZjFhNzMyMjlkZjE2NjZfOFp5YmxGMVBXckQyOE9SclRHaWZjRFpBUGJjRW05TzNfVG9rZW46Ym94Y25Temk4cTdhdndERzdMbDFJQjBNa3BkXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=NGRmNTZhMWYzMmFjYjU0NWU2MzAwNzVlMWIzMjU5NzhfSFJPUWhBYVVRb0VwamxXbUpuTFlXNFNSeTNCcnRjOWNfVG9rZW46Ym94Y25ZTkp5TER0TTFybVFvcWZPOHgxYVZjXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=MDU4Y2U0MzUwNWNjNTU2YzM5ZTY3MzZhZTEyODJlZTVfampqTUpGS0NPc29QZm1XRXZpc1hiOVFhcjl4RlcwSVBfVG9rZW46Ym94Y25xR0h5UHE4cVQ1R256MjBORVlpT2ljXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=YTFiZjI4N2FiNjEzNmU3ZjEzYTI1MDY2MjU5NjRlMWZfc25jTHoxRkFMcHdzMHdXUWtaUkdkWUlzeERDQTdBYkxfVG9rZW46Ym94Y25ySHNIcXM0cDE4SVlkQXc3aVp1ZlRnXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=NDQ2OTU1NjZlOTE0MTY1MjhkNDhmZDEzNzFlNDI2ZmJfaWxEMUtETllaMzVwZ1o1NEZtRjdsbkttbVRrYzBMZjZfVG9rZW46Ym94Y25XQTZxajlkVE5KZlhTY25ySEVJcGhlXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=MmNjZTMwMDBmMDc0OTdmNDcyOTViNDUwYWQ3NmQ5M2JfWXI0eDJidnpma2lXbXBSa2NybnZWV3NITlR1U1AxNzJfVG9rZW46Ym94Y25nR3VhR3JmT3V6NjVVMkxKY045MXhmXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)

![img](https://riw8lxejdn.feishu.cn/space/api/box/stream/download/asynccode/?code=MWI2YWQ1MDk4ZmQyNDMyYzFhNThjZmI0OTk5NzZlMjNfOG11OUlMOHoyQU94dmZ1TWxwZjFmWjVYczY3RkZSS1FfVG9rZW46Ym94Y25RUnZJeE1rb1daWm01dWZpSzlHeW1nXzE2NjY3ODA4Mjc6MTY2Njc4NDQyN19WNA)