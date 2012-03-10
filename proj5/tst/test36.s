	.align 4
body_foo:
!locals=0, max_args=0
	save %sp,-96,%sp
! [MOVE (FIELD (PARAM 0) 0) (CONST 1)]
! [RETURN (FIELD (PARAM 0) 0)]
	ret
	return
	ret
	restore

	.global main
	.align 4
main:
!locals=2, max_args=1
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 