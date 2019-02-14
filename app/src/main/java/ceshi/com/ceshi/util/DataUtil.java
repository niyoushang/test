package ceshi.com.ceshi.util;


import java.util.ArrayList;
import java.util.List;

import ceshi.com.ceshi.bean.VideoBean;

public class DataUtil {

//    public static List<VideoBean> getVideoList() {
//        List<VideoBean> videoList = new ArrayList<>();
//        videoList.add(new VideoBean("七舅脑爷| 脑爷烧脑三重奏，谁动了我的蛋糕",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2018/03/2018-03-30_10-1782811316-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2018/03/29/8b5ecf95be5c5928b6a89f589f5e3637.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 你会不会在爱情中迷失了自我，从而遗忘你正拥有的美好？",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2018/02/2018-02-09_23-573150677-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2018/02/29/056bf3fabc41a1c1257ea7f69b5ee787.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 别因为你的患得患失，就怀疑爱情的重量",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2018/02/2018-02-23_57-2208169443-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2018/02/29/db48634c0e7e3eaa4583aa48b4b3180f.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 女员工遭老板调戏，被同事陷害，双面夹击路在何方？",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/12/2017-12-08_39-829276539-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2017/12/29/fc821f9a8673d2994f9c2cb9b27233a3.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 夺人女友，帮人作弊，不正经的学霸比校霸都可怕。",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2018/01/2018-01-05_49-2212350172-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2018/01/29/bc95044a9c40ec2d8bdf4ac9f8c50f44.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 男子被困秘密房间上演绝命游戏, 背后凶手竟是他?",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/11/2017-11-10_10-320769792-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2017/11/29/15f22f48466180232ca50ec25b0711a7.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 男人玩心机，真真假假，我究竟变成了谁？",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/11/2017-11-03_37-744135043-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2017/11/29/7c21c43ba0817742ff0224e9bcdf12b6.mp4"));
//
//        return videoList;
//    }

    public static List<VideoBean> getVideoList() {
        List<VideoBean> videoList = new ArrayList<>();
        videoList.add(new VideoBean("幸福是什么",
                "https://cms-bucket.nosdn.127.net/171956fc3b0f493482424654b6fb14a520180418140011.jpeg",
                "http://mov.bn.netease.com/open-movie/nos/flv/2015/12/31/SBB7M663L_hd.flv"));

        videoList.add(new VideoBean("群体性孤独",
                "https://cms-bucket.nosdn.127.net/cb37178af1584c1588f4a01e5ecf323120180418133127.jpeg",
                "http://mov.bn.netease.com/open-movie/nos/flv/2015/11/26/SB8EEJKNH_hd.flv"));

        videoList.add(new VideoBean("被拒100天",
                "https://cms-bucket.nosdn.127.net/eb411c2810f04ffa8aaafc42052b233820180418095416.jpeg",
                "http://mov.bn.netease.com/open-movie/nos/flv/2017/01/16/SC9VQFO3E_hd.flv"));

        videoList.add(new VideoBean("什么让我们更热爱自己的工作?",
                "https://cms-bucket.nosdn.127.net/e2af1d563faa46d0aa19da87f83159fd20180418131040.jpeg",
                "http://mov.bn.netease.com/open-movie/nos/flv/2014/01/03/S9GO68OJG_sd.flv"));

        videoList.add(new VideoBean("为什么健康的生活方式几乎把我害死",
                "https://cms-bucket.nosdn.127.net/f90b03a4bac34419b4bc0b22f4d989b420180411100506.jpeg",
                "http://mov.bn.netease.com/open-movie/nos/flv/2013/08/05/S94IVFMKI_hd.flv"));

        videoList.add(new VideoBean("如何掌控你的自由时间？",
                "https://cms-bucket.nosdn.127.net/aae5c06c35d94f45ae3c3108dcb493e520180408212733.jpeg",
                "http://mov.bn.netease.com/open-movie/nos/flv/2017/01/03/SC8U8K7BC_hd.flv"));

        videoList.add(new VideoBean("韩雪：积极的悲观主义者",
                "https://cms-bucket.nosdn.127.net/b963028024f847fe903b1638b05516a120180408212720.jpeg",
                "http://mov.bn.netease.com/open-movie/nos/flv/2017/07/24/SCP786QON_hd.flv"));

        return videoList;
    }

    /**
     * 抖音演示数据
     */
    public static String[] getTikTokVideoList() {
        String videos= "https://file0.antuan.com/2018/dy/6519414669747162381/index.m3u8,https://file0.antuan.com/2018/dy/6519789803192454404/index.m3u8,https://file0.antuan.com/2018/dy/6522641446732827912/index.m3u8,https://file0.antuan.com/2018/dy/6526426132223364366/index.m3u8,https://file0.antuan.com/2018/dy/6528325887673240839/index.m3u8,https://file0.antuan.com/2018/dy/6528548930286783758/index.m3u8,https://file0.antuan.com/2018/dy/6530505931120184579/index.m3u8,https://file0.antuan.com/2018/dy/6530909136308145412/index.m3u8,https://file0.antuan.com/2018/dy/6531267361142476046/index.m3u8,https://file0.antuan.com/2018/dy/6532011252917472515/index.m3u8,https://file0.antuan.com/2018/dy/6532787485297085700/index.m3u8,https://file0.antuan.com/2018/dy/6535738663777602819/index.m3u8,https://file0.antuan.com/2018/dy/6538270537657879816/index.m3u8,https://file0.antuan.com/2018/dy/6538694282654518532/index.m3u8,https://file0.antuan.com/2018/dy/6539413844001819908/index.m3u8,https://file0.antuan.com/2018/dy/6540531038475521288/index.m3u8,https://file0.antuan.com/2018/dy/6540939623403621639/index.m3u8,https://file0.antuan.com/2018/dy/6542390291813371144/index.m3u8,https://file0.antuan.com/2018/dy/6542770419378687239/index.m3u8,https://file0.antuan.com/2018/dy/6543136194392231176/index.m3u8,https://file0.antuan.com/2018/dy/6543514285136416003/index.m3u8,https://file0.antuan.com/2018/dy/6544253374299114756/index.m3u8,https://file0.antuan.com/2018/dy/6544998574315277571/index.m3u8,https://file0.antuan.com/2018/dy/6546122501154409732/index.m3u8,https://file0.antuan.com/2018/dy/6547523848567262471/index.m3u8,https://file0.antuan.com/2018/dy/6547974223703313668/index.m3u8,https://file0.antuan.com/2018/dy/6548344409086233870/index.m3u8,https://file0.antuan.com/2018/dy/6549803340044176647/index.m3u8,https://file0.antuan.com/2018/dy/6550176679468207364/index.m3u8,https://file0.antuan.com/2018/dy/6550933600986991876/index.m3u8,https://file0.antuan.com/2018/dy/6553167166923869448/index.m3u8,https://file0.antuan.com/2018/dy/6553903499103440131/index.m3u8,https://file0.antuan.com/2018/dy/6554183624579616004/index.m3u8,https://file0.antuan.com/2018/dy/6556743617904381187/index.m3u8,https://file0.antuan.com/2018/dy/6557188588419681543/index.m3u8,https://file0.antuan.com/2018/dy/6557643982287932686/index.m3u8,https://file0.antuan.com/2018/dy/6559467219225414919/index.m3u8,https://file0.antuan.com/2018/dy/6559872849006300430/index.m3u8,https://file0.antuan.com/2018/dy/6560183299623357704/index.m3u8,https://file0.antuan.com/2018/dy/6560976277090602253/index.m3u8,https://file0.antuan.com/2018/dy/6561695438326467848/index.m3u8,https://file0.antuan.com/2018/dy/6562064056637721863/index.m3u8,https://file0.antuan.com/2018/dy/6563922741987118350/index.m3u8,https://file0.antuan.com/2018/dy/6564562965889027342/index.m3u8,https://file0.antuan.com/2018/dy/6565432810348219662/index.m3u8,https://file0.antuan.com/2018/dy/6566514493344451843/index.m3u8,https://file0.antuan.com/2018/dy/6566910083257601283/index.m3u8,https://file0.antuan.com/2018/dy/6566957083499957517/index.m3u8,https://file0.antuan.com/2018/dy/6569132188606926094/index.m3u8,https://file0.antuan.com/2018/dy/6569861992666893582/index.m3u8,https://file0.antuan.com/2018/dy/6570199781904223492/index.m3u8,https://file0.antuan.com/2018/dy/6571345464753409293/index.m3u8,https://file0.antuan.com/2018/dy/6571728006895635715/index.m3u8,https://file0.antuan.com/2018/dy/6572090014270754061/index.m3u8,https://file0.antuan.com/2018/dy/6572721818711362829/index.m3u8,https://file0.antuan.com/2018/dy/6572829733489216776/index.m3u8,https://file0.antuan.com/2018/dy/6573942050222247171/index.m3u8,https://file0.antuan.com/2018/dy/6575397729470516488/index.m3u8,https://file0.antuan.com/2018/dy/6575523056809151748/index.m3u8,https://file0.antuan.com/2018/dy/6576527910138547470/index.m3u8,https://file0.antuan.com/2018/dy/6577941935812513031/index.m3u8,https://file0.antuan.com/2018/dy/6579058047337893123/index.m3u8,https://file0.antuan.com/2018/dy/6579773137196420359/index.m3u8,https://file0.antuan.com/2018/dy/6582391984034417927/index.m3u8,https://file0.antuan.com/2018/dy/6582747706211437837/index.m3u8,https://file0.antuan.com/2018/dy/6583121578131721476/index.m3u8,https://file0.antuan.com/2018/dy/6583929109657685252/index.m3u8,https://file0.antuan.com/2018/dy/6584610950630870285/index.m3u8,https://file0.antuan.com/2018/dy/6584976729171823876/index.m3u8,https://file0.antuan.com/2018/dy/6586088165226843400/index.m3u8,https://file0.antuan.com/2018/dy/6586830532057779470/index.m3u8,https://file0.antuan.com/2018/dy/6587574520926702851/index.m3u8,https://file0.antuan.com/2018/dy/6589799167143447812/index.m3u8,https://file0.antuan.com/2018/dy/6590544187865697549/index.m3u8,https://file0.antuan.com/2018/dy/6591722741370457352/index.m3u8,https://file0.antuan.com/2018/dy/6592769050382699784/index.m3u8,https://file0.antuan.com/2018/dy/6592936001478331655/index.m3u8,https://file0.antuan.com/2018/dy/6593507980577082637/index.m3u8,https://file0.antuan.com/2018/dy/6594995995442941191/index.m3u8,https://file0.antuan.com/2018/dy/6596861658868485379/index.m3u8,https://file0.antuan.com/2018/dy/6597960889704385806/index.m3u8,https://file0.antuan.com/2018/dy/6598421822193011976/index.m3u8,https://file0.antuan.com/2018/dy/6598709877000899853/index.m3u8,https://file0.antuan.com/2018/dy/6599535358675782926/index.m3u8,https://file0.antuan.com/2018/dy/6602129354644262158/index.m3u8,https://file0.antuan.com/2018/dy/6603962629230693646/index.m3u8,https://file0.antuan.com/2018/dy/6604648220620492046/index.m3u8,https://file0.antuan.com/2018/dy/6608350574591085828/index.m3u8,https://file0.antuan.com/2018/dy/6608727472689122568/index.m3u8,https://file0.antuan.com/2018/dy/6609471738692504839/index.m3u8,https://file0.antuan.com/2018/dy/6612071015948225800/index.m3u8,https://file0.antuan.com/2018/dy/6613731510434401540/index.m3u8,https://file0.antuan.com/2018/dy/6615412667580419332/index.m3u8,https://file0.antuan.com/2018/dy/6617263432871709966/index.m3u8,https://file0.antuan.com/2018/dy/6618739515668303117/index.m3u8,https://file0.antuan.com/2018/dy/6619116190159604999/index.m3u8,https://file0.antuan.com/2018/dy/6619890995624414472/index.m3u8,https://file0.antuan.com/2018/dy/6620964766636576008/index.m3u8,https://file0.antuan.com/2018/dy/6621341970549705988/index.m3u8,https://file0.antuan.com/2018/dy/6622458446945979662/index.m3u8,https://file0.antuan.com/2018/dy/6623568733321301262/index.m3u8,https://file0.antuan.com/2018/dy/6623932980639304964/index.m3u8,https://file0.antuan.com/2018/dy/6626167914523266311/index.m3u8,https://file0.antuan.com/2018/dy/6627280762699779335/index.m3u8,https://file0.antuan.com/2018/dy/6629878050110573827/index.m3u8,https://file0.antuan.com/2018/dy/6630994793667235075/index.m3u8";
        String[] ss=videos.split(",");

        return ss;
    }
}
