	.align 4
test_foo:
!locals=0, max_args=1
	save %sp,-96,%sp
! [CJUMP <= (PARAM 1) (CONST 1) (NAME L0)]
	nop
! [MOVE (TEMP 1) (CALL (NAME test_bar) ( (PARAM 0)))]
	mov 