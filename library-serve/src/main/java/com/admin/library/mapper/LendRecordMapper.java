package com.admin.library.mapper;

import com.admin.library.domain.entity.LendRecord;
import com.admin.library.domain.vo.LendRecordVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (LendRecord)表数据库访问层
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
public interface LendRecordMapper extends BaseMapper<LendRecord> {

    /**
     * 级联查询借阅记录（三表联查：[lend_record, reader, book]）
     *
     * @return 借阅记录
     */
    // @Select("select r.reader_id,r.username,b.isbn,b.book_name,l.lend_date,l.return_date,l.status from lend_record l " +
    //         "join reader r " +
    //         "on r.reader_id = l.reader_id " +
    //         "join book b " +
    //         "on b.isbn = l.isbn " +
    //         "where r.username like '%${username}%' " +
    //         "and b.book_name like '%${bookName}%' " +
    //         "and r.reader_id = #{readerId} " +
    //         "limit #{skip},#{size}")
    List<LendRecordVo> selectAll(@Param("skip") long skip, @Param("size") long size, @Param("username") String username, @Param("bookName") String bookName, @Param("readerId") Long readerId);
}

