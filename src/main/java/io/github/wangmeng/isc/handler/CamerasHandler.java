package io.github.wangmeng.isc.handler;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import io.github.wangmeng.isc.config.CamerasProperties;
import io.github.wangmeng.isc.exception.CameraException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CamerasHandler {

    @Autowired
    private CamerasProperties camerasProperties;


    /**
    *  获取区域列表接口可用来全量同步区域信息，返回结果分页展示。
     * 注：综合安防管理平台iSecure Center V1.1.0 查询的区域不包括级联区域。
     * 综合安防管理平台iSecure Center V1.2.0查询的区域包括非级联区域和级联区域。
     *
     * 接口适配产品版本
     *
     * 综合安防管理平台iSecure Center V1.1.0及以上版本
     *
     * 接口版本
     *
     * v1
     *
     * 接口地址
     *
     * /api/resource/v1/regions
     *
     * 请求方法
     *
     * POST
     *
     * 数据提交方式
     *
     * application/json
     *
     参数名称	数据类型	参数描述	是否必须	备注
     pageNo	Number	当前页码	是	0 < pageNo
     pageSize	Number	分页大小	是	范围 ( 0 , 1000 ] 注：0 < pageSize<=1000
     treeCode	String	树编号	否	树编号（默认0，0代表国标树） 此字段为预留字段，暂时不用。 最大长度：3
    */
    public String regionsRoot(Integer pageNo, Integer pageSize){
        checkPageParam(pageNo,pageSize);
        checkAuthParam(camerasProperties);

        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        ArtemisConfig.host = camerasProperties.getHost();
        // 秘钥Appkey
        ArtemisConfig.appKey = camerasProperties.getAppKey();
        // 秘钥AppSecret
        ArtemisConfig.appSecret = camerasProperties.getAppSecret();

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        String getRootApi = ARTEMIS_PATH + "/api/resource/v1/regions";
        Map<String, String> path = new HashMap<String, String>(2);
        //根据现场环境部署确认是http还是https
        path.put("http://", getRootApi);
        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", pageNo);
        jsonBody.put("pageSize", pageSize);
        String body = jsonBody.toJSONString();

        /**
         * STEP6：调用接口
         */
        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType);
        return result;
    }

    
    
    /**
    *  根据区域编号获取监控点信息列表
     * 接口说明
     *
     * 根据指定的区域编号获取该区域下的监控点列表信息，返回结果分页展示。
     * 注：返回的监控点不包括下级区域的。
     *
     * 接口适配产品版本
     *
     * 综合安防管理平台iSecure Center V1.1.0及以上版本
     *
     * 接口版本
     *
     * v1
     *
     * 接口地址
     *
     * /api/resource/v1/regions/regionIndexCode/cameras
     *
     * 请求方法
     *
     * POST
     *
     * 数据提交方式
     *
     * application/json
    */
    public String regionsCameras(String regionIndexCode, Integer pageNo, Integer pageSize){
        checkPageParam(pageNo,pageSize);
        checkAuthParam(camerasProperties);
        if (StringUtils.isEmpty(regionIndexCode)){
            log.error("regionIndexCode is empty");
            throw new CameraException("regionIndexCode is empty");
        }
        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        ArtemisConfig.host = camerasProperties.getHost();
        // 秘钥Appkey
        ArtemisConfig.appKey = camerasProperties.getAppKey();
        // 秘钥AppSecret
        ArtemisConfig.appSecret = camerasProperties.getAppSecret();


        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        String getRootApi = ARTEMIS_PATH + "/api/resource/v1/regions/regionIndexCode/cameras";
        Map<String, String> path = new HashMap<String, String>(2);
        //根据现场环境部署确认是http还是https
        path.put("http://", getRootApi);

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", pageNo);
        jsonBody.put("pageSize", pageSize);
        jsonBody.put("regionIndexCode", regionIndexCode);
        String body = jsonBody.toJSONString();

        /**
         * STEP6：调用接口
         */
        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType);
        return result;
    }
    
    
    
    /**
    *  获取监控点预览取流URL
    * 接口说明
    *
    * 平台正常运行；平台已经添加过设备和监控点信息。
    *
    * 合作厂商平台通过OpenAPI获取到监控点数据，依据自身业务开发监控点导航界面。
    *
    * 接口适配产品版本
    *
    * 综合安防管理平台iSecure Center V1.1.0及以上版本
    *
    * 接口版本
    *
    * v1
    *
    * 接口地址
    *
    * /api/video/v1/cameras/previewURLs
    *
    * 请求方法
    *
    * POST
    *
    * 数据提交方式
    *
    * application/json
    *
    参数名称	数据类型	参数描述	是否必须	备注
    cameraIndexCode	String	监控点唯一标识	是	根据【监控点信息】接口获取返回参数cameraIndexCode。
    streamType	Number	码流类型	否	0:主码流，1:子码流，2:第三码流，参数不填，默认为主码流
    protocol	String	取流协议（应用层协议）	否	“rtsp”:RTSP协议,“rtmp”:RTMP协议,“hls”:HLS协议（HLS协议只支持海康SDK协议、EHOME协议、GB28181协议、ONVIF协议接入的设备；只支持H264视频编码和AAC音频编码）,参数不填，默认为RTSP协议
    transmode	Number	传输协议（传输层协议）	否	0:UDP，1:TCP，默认是TCP，注：GB28181 2011版本接入设备的预览只支持UDP传输协议，GB28181 2016版本接入设备的预览支持UDP和TCP传输协议
    expand	String	扩展内容	否	标识扩展内容，格式：key=value， 调用方根据其播放控件支持的解码格式选择相应的封装类型； 支持的内容详见附录F expand扩展内容说明，如无特殊需求，建议不指定expand
    */
    public String cameras_previewURLs(String cameraIndexCode, Integer streamType, String protocol, Integer transmode){
        checkAuthParam(camerasProperties);
        if (StringUtils.isEmpty(cameraIndexCode)){
            log.error("cameraIndexCode is empty");
            throw new CameraException("cameraIndexCode is empty");
        }

        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        ArtemisConfig.host = camerasProperties.getHost();
        // 秘钥Appkey
        ArtemisConfig.appKey = camerasProperties.getAppKey();
        // 秘钥AppSecret
        ArtemisConfig.appSecret = camerasProperties.getAppSecret();

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String getCamsApi = ARTEMIS_PATH + "/api/video/v1/cameras/previewURLs";
        Map<String, String> path = new HashMap<String, String>(2);
        //根据现场环境部署确认是http还是https
        path.put("http://", getCamsApi);

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cameraIndexCode", cameraIndexCode);
        jsonBody.put("streamType", streamType == null || streamType == 0 ? 0 : streamType); // 默认O
        jsonBody.put("protocol", StringUtils.isEmpty(protocol) ? "rtsp" : protocol); // 默认RTSP
        jsonBody.put("transmode", streamType == null || streamType == 0 ? 1 : transmode); // 默认tcp
//        jsonBody.put("expand", "streamform=ps");
        String body = jsonBody.toJSONString();

        /**
         * STEP6：调用接口
         */
        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType, null);
        return result;
    }
    
    
    


    private void checkPageParam(Integer pageNo, Integer pageSize){
        if (0 > pageNo || 0 > pageSize || 1000 < pageSize ){
            log.error("请求参数有误");
            throw new CameraException("请求参数有误");
        }
    }


    private void checkAuthParam(CamerasProperties camerasProperties){
        if (camerasProperties == null || camerasProperties.getHost() == null
                || camerasProperties.getAppKey() == null
                || camerasProperties.getAppSecret() == null){
            log.error("主机相关相关信息缺失");
            throw new CameraException("主机相关相关信息缺失");
        }
    }





}
