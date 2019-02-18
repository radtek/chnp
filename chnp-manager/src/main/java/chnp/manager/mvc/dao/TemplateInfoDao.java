package chnp.manager.mvc.dao;

import chnp.manager.mvc.model.domain.TemplateInfo;
import chnp.manager.mvc.model.query.TemplateInfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TemplateInfoDao {

    /**<p>插入数据</p>
     * @param templateInfo 待存储的数据对象
     */
    Integer save(TemplateInfo templateInfo);

    /**<p>按条件删除数据</p>
     * @param templateInfoQuery 查询条件
     */
    Integer deleteByCondition(TemplateInfoQuery templateInfoQuery);

    /**<p>根据条件查询数据</p>
     * @param templateInfoQuery 查询条件
     * @return 若存在数据，则返回数组；否则返回null
     */
    List<TemplateInfo> findByCondition(TemplateInfoQuery templateInfoQuery);

    /**<p>根据条件统计数据</p>
     * @param templateInfoQuery 查询条件
     * @return 若存在数据，则返回数量；否则返回null
     */
    Long countByCondition(TemplateInfoQuery templateInfoQuery);

    /**<p>根据主键数据</p>
     * @param templateInfo 待更新的数据对象。主键不能为空
     */
    Integer updateById(TemplateInfo templateInfo);
}