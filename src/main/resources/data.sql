INSERT IGNORE INTO `authorities` (`username`, `authority`) VALUES
	('force022', 'ROLE_FORCE022'),
	('kineko', 'ROLE_KINEKO');

INSERT IGNORE INTO `groups` (`id`, `group_name`) VALUES
	(1, 'ADMIN'),
	(2, 'USER');

INSERT IGNORE INTO `group_authorities` (`group_id`, `authority`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER');

INSERT IGNORE INTO `group_members` (`id`, `username`, `group_id`) VALUES
	(1, 'kineko', 1),
	(2, 'kineko', 2),
	(3, 'force022', 2);

INSERT IGNORE INTO `users` (`username`, `password`, `enabled`) VALUES
	('force022', '$2a$10$8Zyzlta4Ma2f8KQq75g/EeSDIFZ00qbvu3NjO/lq9kxE2vAHGb9LO', 1),
	('kineko', '$2a$10$.LtXHGrNS3aVCFiSjhyE.eoItSJemnTmJv9aqTYAH8a3EpFf9uQD.', 1);
