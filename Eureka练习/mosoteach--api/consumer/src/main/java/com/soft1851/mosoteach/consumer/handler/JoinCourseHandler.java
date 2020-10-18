package com.soft1851.mosoteach.consumer.handler;

import com.alibaba.fastjson.JSON;
import com.soft1851.mosoteach.consumer.entity.Course;
import com.soft1851.mosoteach.consumer.entity.User;
import com.soft1851.mosoteach.consumer.vo.JoinCourseVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/consumer")
@RestController
public class JoinCourseHandler {
    @Resource
    private RestTemplate restTemplate;

    /**
     * 查找所有班课
     *
     * @return
     */
    @GetMapping("/course/all")
    public List<Course> getAllCourse() {
        return (List<Course>) restTemplate.getForObject("http://10.40.198.253:8002/api/course/all", List.class);
    }


    /**
     * 根据用户id查找已加入的班课
     *
     * @param uid
     * @return
     */
    @PostMapping("/course/listbyid/{uid}")
    public List getCListById(@PathVariable int uid) {
        String listJson = JSON.toJSONString(restTemplate.getForObject("http://xxq.com:8002/api/course/byuid/" + uid, List.class));

        List<JoinCourseVo> joinCourseVoList = JSON.parseArray(listJson, JoinCourseVo.class);

        for (int i = 0; i < joinCourseVoList.size(); i++) {
            JoinCourseVo joinCourseVo = joinCourseVoList.get(i);
            Integer tId = joinCourseVo.getTId();
            User teacher = restTemplate.postForObject("http://xxq.com:8001/api/user/getById/" + tId, tId, User.class);
            joinCourseVo.setTName(teacher.getUName());
        }
        return joinCourseVoList;
    }

}
