# Android app 使用 蒲公英 版本更新 说明

#两种接入方式：

1、使用蒲公英提供的类直接调用方法
    
    a、拷贝本项目中的 com.pgyer.pgyerupdateversiondemo.pgyerutils 文件夹中的的三个文件到Android 项目中
    b、在需要获取更新版本的位置，调用类中提供的方法方法PgyUpdateVersion.checkVersionUpdate 
        PgyUpdateVersion.checkVersionUpdate("您的apiKey","您的appKey","","build.gradle 中的 versionName",new PgyCheckoutCallBack() {

                    @Override
                    public void onNewVersionExist(PgyCheckSoftModel model) {
                        tvShow.setText("有新版本："+JsonUtils.toJSONString(model));

                    }

                    @Override
                    public void onNonentityVersionExist(String error) {
                        tvShow.setText("无新版本"+error);
                    }

                    @Override
                    public void onFail(String error) {
                        tvShow.setText("请求失败："+error);
                    }
                });
    c、在onNewVersionExist 中那到新版本中的apk信息，（PgyCheckSoftModel 已备注具体字段说明）

2、有自己的网络访问框架

    a、自行调用蒲公英版本检查接口 （接口说明文档：https://www.pgyer.com/doc/view/api#appUpdate 支持GET 和 POST 请求） 
    b、得到返回数据自行实现相关更新逻辑
