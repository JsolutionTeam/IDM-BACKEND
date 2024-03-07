# 아이폰 15 프로 맥스
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '아이폰 15 프로 맥스%'
order by c.name asc;

# 아이폰 15 프로
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '아이폰 15 프로 %'
  and d.pet_nm not like '%맥스%'
order by c.name asc;

# 아이폰 15 플러스
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '아이폰 15 플러스%'
order by c.name asc;

# 아이폰 15
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '아이폰 15'
order by c.name asc;

# 갤럭시 Z 플립 5
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 Z 플립 5%'
order by c.name asc;

# 갤럭시 Z 폴드 5
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 Z 폴드 5%'
order by c.name asc;

# 갤럭시 A24 LTE
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 A24 LTE%'
order by c.name asc;

# 갤럭시 Z 폴드 4
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 Z 폴드 4%'
order by c.name asc;

# 아이폰 SE 3세대
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '아이폰 SE 3세대%'
order by c.name asc;

# 갤럭시 S23+
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 S23+%'
order by c.name asc;

# 갤럭시 S23
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm = '갤럭시 S23'
order by c.name asc;

# 갤럭시 Z 플립 4
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 Z 플립 4%'
order by c.name asc;
