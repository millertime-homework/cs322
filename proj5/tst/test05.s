	.global main
	.align 4
main:
!locals=1, max_args=1
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [CALLST (NAME Body_go) ( (VAR 1))]
	call Body_go
	nop
	ret
	restore

	.align 4
Body_go:
!locals=0, max_args=0
	save %sp,-96,%sp
! [CALLST (NAME print) ( (STRING "Go!"))]
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"
L$2:	.asciz "Go!\n"

!Total regs:  2
!Total insts: 26
