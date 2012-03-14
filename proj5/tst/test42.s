	.global main
	.align 4
main:
!locals=1, max_args=2
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
! [MOVE (TEMP 2) (CALL (NAME A_getfval) ( (VAR 1) (CONST 5)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	ld [5],%l2
	st %l2,[%sp+68]
	call A_getfval
	nop
	mov %o0,%l3
!>> Temp t2 assigned to reg %l4
	mov %o0,%l4
! [CALLST (NAME print) ( (TEMP 2))]
	ld [%l4],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

	.align 4
A_getfval:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (VAR 1) (FLOAT 3.1)]
