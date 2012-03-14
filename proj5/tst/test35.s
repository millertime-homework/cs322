	.align 4
body_foo:
!locals=0, max_args=0
	save %sp,-96,%sp
! [RETURN (BINOP + (PARAM 1) (PARAM 2))]
	ret
	restore
	ret
	restore

	.global main
	.align 4
main:
!locals=2, max_args=3
	save %sp,-104,%sp
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
! [MOVE (TEMP 2) (CALL (NAME body_foo) ( (VAR 1) (CONST 1) (CONST 2)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	ld [1],%l2
	st %l2,[%sp+68]
	ld [2],%l3
	st %l3,[%sp+68]
	call body_foo
	nop
	mov %o0,%l4
!>> Temp t2 assigned to reg %l5
	mov %o0,%l5
! [MOVE (VAR 2) (TEMP 2)]
	mov %l5,%l4
	st %l4,[%fp-8]
! [CALLST (NAME print) ( (VAR 2))]
	ld [%fp-8],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  7
!Total insts: 38
