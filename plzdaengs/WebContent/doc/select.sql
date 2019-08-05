-- 강아지 품종별 랭킹 뽑아내는 sql
select *
from (
        select
            rownum as ranking
            , pet_breed_group_count.*
        from (
                select
                    pet.animal_code
                    , breed.breed_name
                    , pet.breed_code
                    , animal.animal_name
                    , count(*) as count
                from 
                    plz_pet pet
                    left outer join plz_breed breed
                        on pet.animal_code = breed.animal_code
                        and pet.breed_code = breed.breed_code
                    left outer join plz_animal animal
                        on breed.animal_code = animal.animal_code
                group by pet.animal_code, pet.breed_code, breed.breed_name, animal.animal_name
                order by count desc
            ) pet_breed_group_count
    )
where ranking <= 10
;

-- 해당 품종에 대한 평균값 구하기
select
    pet_gender
    , ceil(avg(age)) age
    , count(*) count
from (
        select
            to_number(to_char(sysdate, 'yyyy')) - to_number(to_char(pet.birth_date, 'yyyy')) + 1 as age
            , pet.pet_gender
        from 
            plz_pet pet
            left outer join plz_breed breed
                on pet.animal_code = breed.animal_code
                and pet.breed_code = breed.breed_code
            left outer join plz_animal animal
                on breed.animal_code = animal.animal_code
        where 
            breed.breed_name = '포메라니안'
    ) breedAge
group by pet_gender
;