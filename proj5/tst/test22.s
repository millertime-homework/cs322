	.align 4
test_foo:
!locals=1, max_args=0
	save %sp,-96,%sp
! [RETURN (PARAM 1)]
	ret
	return
	ret
	restore

	.align 4
test_bar:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (VAR 1) (CONST 2)]
! [RETURN (VAR 1)]
	ret
	return
	ret
	restore

	.global main
	.align 4
main:
!locals=2, max_args=2
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME test_foo) ( (PARAM 0) (CONST 1)))]
	mov 