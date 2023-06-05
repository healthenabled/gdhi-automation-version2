--Run below for FE Pipeline.

-- delete health indicators
DELETE FROM country_health_data.health_indicators WHERE country_id = 'LKA' and year = '2023';

-- delete resources
DELETE FROM country_health_data.country_resource_links WHERE country_id = 'LKA' and year = '2023';

-- delete country summary
DELETE FROM country_health_data.country_summary WHERE country_id = 'LKA' and year = '2023';

-- delete country phase
DELETE FROM country_health_data.country_phase WHERE country_id = 'LKA' and year = '2023';

--Run below for BE Pipeline.

-- delete health indicators
DELETE FROM country_health_data.health_indicators WHERE country_id = 'NPL' and year = '2023';

-- delete resources
DELETE FROM country_health_data.country_resource_links WHERE country_id = 'NPL' and year = '2023';

-- delete country summary
DELETE FROM country_health_data.country_summary WHERE country_id = 'NPL' and year = '2023';

-- delete country phase
DELETE FROM country_health_data.country_phase WHERE country_id = 'NPL' and year = '2023';
