package cyf.userinfo.service;

import cyf.userinfo.dao.UserInfoMapper;
import cyf.userinfo.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by Administrator on 2017-2-8.
 */
@Service
public class UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    public List<UserInfo> getUserInfoList(UserInfo record){

        return userInfoMapper.getUserInfoList(record);
    }
    public Integer getUserInfoListCount(UserInfo record){
        return userInfoMapper.getUserInfoListCount(record);
    }

  public int insertSelective(UserInfo record){
        record.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return userInfoMapper.insertSelective(record);
    }

  public  UserInfo selectByPrimaryKey(Integer id){
        return userInfoMapper.selectByPrimaryKey(id);
    }

   public int deleteByPrimaryKey(Integer id){
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserInfo record){
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }
    public  UserInfo getUserInfoByLoginName(String name){
        return userInfoMapper.getUserInfoByLoginName(name);
    }
}
