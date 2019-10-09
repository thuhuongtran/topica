SELECT * FROM jv8_student_subject as ss JOIN 
jv8_student AS s JOIN jv8_class AS c 
JOIN jv8_school AS sc
WHERE sc.id = c.school_id 
AND c.id = s.class_id
AND s.id = ss.st_id
ORDER BY sc.id AND c.id ;

SELECT s.id AS stid, c.id AS cid, sc.id AS scid 
FROM jv8_student AS s 
JOIN jv8_class AS c 
JOIN jv8_school AS sc
WHERE c.id = s.class_id
AND c.school_id = sc.id
GROUP BY stid
ORDER BY scid ASC,cid ASC, stid ASC;

SELECT c.id AS cid, sc.id AS scid
FROM jv8_class AS c 
JOIN jv8_school AS sc
WHERE c.school_id = sc.id
GROUP BY c.id
ORDER BY scid ASC,cid asc;


SELECT ss.sj_id AS sjid, s.id AS stid, 
c.id AS cid, sc.id AS scid 
FROM jv8_student_subject AS ss
JOIN jv8_student AS s 
JOIN jv8_class AS c 
JOIN jv8_school AS sc
WHERE ss.st_id = s.id
and c.id = s.class_id
AND c.school_id = sc.id
AND sc.id = 1 AND c.id =5 
ORDER BY scid ASC,cid ASC,sjid ASC, stid ASC;


SELECT ss.sj_id AS sjid, s.id AS stid, 
c.id AS cid, sc.id AS scid
FROM jv8_student_subject AS ss
JOIN jv8_student AS s 
JOIN jv8_subject AS sj
JOIN jv8_class AS c 
JOIN jv8_school AS sc
WHERE ss.st_id = s.id
AND ss.sj_id = sj.id
and c.id = s.class_id
AND c.school_id = sc.id
AND sc.id = 1 and sj.domain='GEOGRAPHY'
ORDER BY scid ASC,cid ASC,sjid ASC, stid ASC;

SELECT ss.sj_id AS sjid, s.id AS stid, 
c.id AS cid, sc.id AS scid , MAX(ss.score) AS score,
sj.domain
FROM jv8_student_subject AS ss
JOIN jv8_student AS s 
JOIN jv8_subject AS sj
JOIN jv8_class AS c 
JOIN jv8_school AS sc
WHERE ss.st_id = s.id
AND ss.sj_id = sj.id
and c.id = s.class_id
AND c.school_id = sc.id
AND sc.id = 1
ORDER BY scid ASC,cid ASC,sjid ASC, stid ASC;


SELECT sc.id AS scid , c.id as cid, sj.domain,
avg(ss.score) AS avga
FROM jv8_student_subject AS ss
JOIN jv8_student AS s 
JOIN jv8_subject AS sj
JOIN jv8_class AS c 
JOIN jv8_school AS sc
WHERE ss.st_id = s.id
AND ss.sj_id = sj.id
and c.id = s.class_id
AND c.school_id = sc.id
AND sc.id = 1 AND domain = 'PHYSICS' AND c.id = 18
GROUP BY cid
ORDER BY scid ASC,cid ASC;
