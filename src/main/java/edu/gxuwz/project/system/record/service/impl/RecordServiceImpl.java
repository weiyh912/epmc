package edu.gxuwz.project.system.record.service.impl;

import edu.gxuwz.common.utils.DateUtils;
import edu.gxuwz.common.utils.text.Convert;
import edu.gxuwz.project.system.dept.domain.Dept;
import edu.gxuwz.project.system.dept.service.IDeptService;
import edu.gxuwz.project.system.record.domain.Record;
import edu.gxuwz.project.system.record.mapper.RecordMapper;
import edu.gxuwz.project.system.record.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 记录Service业务层处理
 * 
 * @author epmc
 * @date 2020-02-20
 */
@Service
public class RecordServiceImpl implements IRecordService 
{
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private IDeptService deptService;

    /**
     * 查询记录
     * 
     * @param recordId 记录ID
     * @return 记录
     */
    @Override
    public Record selectRecordById(Long recordId)
    {
        return recordMapper.selectRecordById(recordId);
    }

    /**
     * 查询记录列表
     * 
     * @param record 记录
     * @return 记录
     */
    @Override
    public List<Record> selectRecordList(Record record)
    {
        return recordMapper.selectRecordList(record);
    }

    @Override
    public List<Record> selectRecordList1(Record record) {
        return recordMapper.selectRecordList1(record);
    }

    @Override
    public List<Record> bumen(Record record)
    {
        return recordMapper.bumen(record);
    }
    @Override
    public List<Record> geren(Record record)
    {
        return recordMapper.geren(record);
    }
    @Override
    public List<Record> xuesheng(Record record)
    {
        return recordMapper.xuesheng(record);
    }
    @Override
    public List<Record> jiaozhigong(Record record)
    {
        return recordMapper.jiaozhigong(record);
    }

    /**
     * 新增记录
     * 
     * @param record 记录
     * @return 结果
     */
    @Override
    public int insertRecord(Record record)
    {
        return recordMapper.insertRecord(record);
    }

    /**
     * 修改记录
     * 
     * @param record 记录
     * @return 结果
     */
    @Override
    public int updateRecord(Record record)
    {
        return recordMapper.updateRecord(record);
    }

    /**
     * 删除记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRecordByIds(String ids)
    {
        return recordMapper.deleteRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除记录信息
     * 
     * @param recordId 记录ID
     * @return 结果
     */
    @Override
    public int deleteRecordById(Long recordId)
    {
        return recordMapper.deleteRecordById(recordId);
    }

    @Override
    public Record selectRecordByDateAndId(Date recordDate, String recordNumber) {
        return recordMapper.selectRecordByDateAndId(recordDate, recordNumber);
    }

    @Override
    public List<Record> selectYichang(Record record) {
        return recordMapper.selectYichang(record);
    }

    @Override
    public List<Map<String, Object>> huizong(Record record) {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        List<Dept> depts = deptService.selectDeptListNotChails();
        if(depts != null){
            for (int i=0; i<depts.size(); i++){
                Dept dept = depts.get(i);
                record.setDeptId(dept.getDeptId());
                Map<String, Object> huizong = recordMapper.huizong(record);
                huizong.put("dept", dept);
                huizong.put("date", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, record.getRecordDate()));
                maps.add(huizong);
            }
        }
        return maps;
    }

}
