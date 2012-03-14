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
! [MOVE (TEMP 2) (CALL (NAME A_go) ( (VAR 1)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	call A_go
	nop
	mov %o0,%l2
!>> Temp t2 assigned to reg %l3
	mov %o0,%l3
! [CALLST (NAME print) ( (TEMP 2))]
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

	.align 4
B_back:
!locals=0, max_args=0
	save %sp,-96,%sp
! [RETURN (PARAM 1)]
	ret
	restore
	ret
	restore

	.align 4
A_go:
!locals=0, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 3) (CALL (NAME B_back) ( (PARAM 0) (CONST 2)))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	ld [2],%l1
	st %l1,[%sp+68]
	call B_back
	nop
	mov %o0,%l2
!>> Temp t3 assigned to reg %l3
	mov %o0,%l3
! [RETURN (TEMP 3)]
	ret
	restore
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  5
!Total insts: 47
