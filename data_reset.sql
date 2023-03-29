-- delete health indicators
DELETE FROM country_health_data.health_indicators WHERE country_id = 'LKA';

-- delete resources
DELETE FROM country_health_data.country_resource_links WHERE country_id = 'LKA';

--delete from country_health_data.development_indicators where country_id='LKA';

-- delete country summary
DELETE FROM country_health_data.country_summary WHERE country_id = 'LKA';

--Run below for BE Pipeline.

-- delete health indicators
DELETE FROM country_health_data.health_indicators WHERE country_id = 'NPL';

-- delete resources
DELETE FROM country_health_data.country_resource_links WHERE country_id = 'NPL';

--delete from country_health_data.development_indicators where country_id='NPL';

-- delete country summary
DELETE FROM country_health_data.country_summary WHERE country_id = 'NPL';

-- delete country phase
DELETE FROM country_health_data.country_phase WHERE country_id = 'NPL';
